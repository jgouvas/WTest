package com.wtest.wtest.articles.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleItemListResponse {

    @SerializedName("items")
    private List<ArticleResponse> items;

    public ArticleItemListResponse(List<ArticleResponse> items) {
        this.items = items;
    }

    public List<ArticleResponse> getItems() {
        return items;
    }
}
