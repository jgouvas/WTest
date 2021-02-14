package com.wtest.wtest.articles;

import java.util.Date;

public class Article {

    private int id;
    private String title;
    private Date publishedAt;
    private String hero;
    private String author;
    private String summary;
    private String body;

    public Article(int id, String title, Date publishedAt, String hero, String author, String summary, String body) {
        this.id = id;
        this.title = title;
        this.publishedAt = publishedAt;
        this.hero = hero;
        this.author = author;
        this.summary = summary;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public String getHero() {
        return hero;
    }

    public String getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public String getBody() {
        return body;
    }
}
