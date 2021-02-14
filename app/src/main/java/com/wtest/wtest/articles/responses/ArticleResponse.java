package com.wtest.wtest.articles.responses;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ArticleResponse {

    @SerializedName("id") private int id;
    @SerializedName("title") private String title;
    @SerializedName("published-at") private Date publishedAt;
    @SerializedName("hero") private String hero;
    @SerializedName("author") private String author;
    @SerializedName("summary") private String summary;
    @SerializedName("body") private String body;

    public ArticleResponse(int id, String title, Date publishedAt, String hero, String author, String summary, String body) {
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
