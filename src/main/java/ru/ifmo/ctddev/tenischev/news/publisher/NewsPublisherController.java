package ru.ifmo.ctddev.tenischev.news.publisher;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import ru.ifmo.ctddev.tenischev.news.publisher.dto.News;

/**
 * This class is provide service to store {@link News} in news-storage.
 *
 * @author setenish 29.06.2019.
 */
@Path("/news")
@Singleton
public class NewsPublisherController {

    /**
     * Host name of news-storage.
     */
    @Inject
    @ConfigProperty(name = "news.repository.host")
    private String newsRepoHost;

    /**
     * Port of news-storage.
     */
    @Inject
    @ConfigProperty(name = "news.repository.port")
    private String newsRepoPort;

    /**
     * Path of news-storage.
     */
    @Inject
    @ConfigProperty(name = "news.repository.path")
    private String newsRepoPath;

    /**
     * Handles request from form by creating the {@link News} object and transmitting to news-storage.
     *
     * @param title
     *            the title of news
     * @param text
     *            the text of news
     * @param publisher
     *            the publisher of news
     * @param request
     *            the request context
     * @param response
     *            the response context
     */
    @POST
    @Path("/add")
    public void mainPage(@FormParam("title") String title, @FormParam("text") String text,
            @FormParam("publisher") String publisher, @Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        String result = publishNews(collectNews(title, text, publisher));
        buildResponse(result, request, response);
    }

    /**
     * Transmit news to news-storage.
     * 
     * @param news
     *            the news
     * @return result of transmitting.
     */
    @Fallback(fallbackMethod = "publisherFallback") // better use FallbackHandler
    @Timeout(500)
    private String publishNews(News news) {
        try {
            URI apiUri = new URI("http://" + newsRepoHost + ":" + newsRepoPort + "/" + newsRepoPath);
            NewsRepositoryService newsRepositoryService = RestClientBuilder.newBuilder().baseUri(apiUri)
                    .build(NewsRepositoryService.class);
            newsRepositoryService.addNews(news);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "Wrong URL of news-storage";
        }
        return "News has been successfully added";
    }

    /**
     * This methods builds response structure which redirect to main page and uses provided responseInfo as
     * "response-status" header.
     * 
     * @param responseInfo
     *            the result of operation that will be transmitted to user
     * @param request
     *            the request context
     * @param response
     *            the response context
     */
    private void buildResponse(String responseInfo, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/AdNewsPublisher/index.jsp?response-status=" + responseInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fallback method in case of timeout to request of news-storage.
     * 
     * @param news
     *            the news object
     * @return reason of fault.
     */
    public String publisherFallback(News news) {
        return "Couldn't reach storage!";
    }

    /**
     * Cuts fields to allowed length and combine into one {@link News} object.
     * 
     * @param title
     *            the title of news
     * @param text
     *            the text of news
     * @param publisher
     *            the publisher of news
     * @return the news object
     */
    private News collectNews(String title, String text, String publisher) {
        title = StringUtils.left(title, Constants.TITLE_MAX_LENGTH);
        text = StringUtils.left(text, Constants.TEXT_MAX_LENGTH);
        publisher = StringUtils.left(publisher, Constants.PUBLISHER_MAX_LENGTH);

        return News.builder().topic(title).text(text).publisher(publisher).build();
    }
}
