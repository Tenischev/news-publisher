package ru.ifmo.ctddev.tenischev.news.publisher;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;

import ru.ifmo.ctddev.tenischev.news.publisher.dto.News;

/**
 * REST interface for new-storage.
 */
@Path("/")
public interface NewsRepositoryService {

    /**
     * Method to add new news.
     * 
     * @param news
     *            the {@link News} object
     */
    @PUT
    @Path("/add")
    void addNews(News news);
}
