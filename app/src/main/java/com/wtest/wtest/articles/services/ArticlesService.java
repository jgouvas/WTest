package com.wtest.wtest.articles.services;


import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.wtest.wtest.BuildConfig;
import com.wtest.wtest.articles.api.ArticlesApi;
import com.wtest.wtest.articles.responses.ArticleItemListResponse;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArticlesService {

    private ArticlesApi api;

    public ArticlesService() {
        initApi();
    }

    public Single<ArticleItemListResponse> getArticles() {
        return api.getArticles();
    }


    private void initApi() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        api = retrofit.create(ArticlesApi.class);
    }
}
