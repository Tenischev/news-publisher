package ru.ifmo.ctddev.tenischev.news.publisher;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import io.cucumber.java8.En;
import ru.ifmo.ctddev.tenischev.news.publisher.dto.News;

/**
 * This class is
 *
 * @author setenish 07.09.2019.
 */
public class PublisherStepdefs implements En {
    private String date;

    private News resultNews;

    private String publisher;

    private String text;

    private String title;

    public PublisherStepdefs() {
        Given("new News with title {string}", (String title) -> this.title = title);
        And("text {string}", (String text) -> this.text = text);
        And("publisher {string}", (String publisher) -> this.publisher = publisher);
        And("^expiration time is OK$",
                () -> this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
        When("^I collect this News$", () -> resultNews = collectNews(title, text, publisher));
        Then("I should have News with this title {string}",
                (String title) -> assertEquals(title, resultNews.getTopic()));
        Then("I should have News with this text {string}", (String text) -> assertEquals(text, resultNews.getText()));
        Then("I should have News with this publisher {string}",
                (String publisher) -> assertEquals(publisher, resultNews.getPublisher()));

        Given("^new News with title, text, publisher$", () -> {
            text = "text";
            title = "title";
            publisher = null;
        });
        And("^expiration time is yesterday$",
                () -> date = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
        And("^expiration time is in next year$",
                () -> date = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
        Then("^I should have News with expiration date tomorrow$",
                () -> assertEquals(LocalDate.now().plusDays(1).atStartOfDay(),
                        resultNews.getExpirationTime().toLocalDateTime()));
    }

    private News collectNews(String title, String text, String publisher)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        NewsPublisherController controller = new NewsPublisherController();
        Class<?> clazz = controller.getClass();
        Method method = clazz.getDeclaredMethod("collectNews", String.class, String.class, String.class, String.class);
        method.setAccessible(true);
        return (News) method.invoke(controller, title, text, publisher, date);
    }
}
