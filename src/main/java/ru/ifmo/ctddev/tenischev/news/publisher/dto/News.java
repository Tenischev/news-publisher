package ru.ifmo.ctddev.tenischev.news.publisher.dto;

import lombok.Builder;
import lombok.Data;

/**
 * This class is DTO for the news.
 *
 * @author setenish 29.06.2019.
 */
@Builder
public @Data class News {

    /**
     * The topic or title of news.
     */
    private String topic;

    /**
     * The text of news.
     */
    private String text;

    /**
     * The publisher of news.
     */
    private String publisher;
}
