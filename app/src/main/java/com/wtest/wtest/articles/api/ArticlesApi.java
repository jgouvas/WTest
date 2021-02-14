package com.wtest.wtest.articles.api;

import com.wtest.wtest.articles.responses.ArticleItemListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ArticlesApi {

    @GET("articles")
    Single<ArticleItemListResponse> getArticles();

    //https://5bb1cd166418d70014071c8e.mockapi.io/mobile/1-1/articles
    //https://5badefb9a65be000146763a4.mockapi.io/mobile/1-0/articles
}
