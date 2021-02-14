package com.wtest.wtest.articles.interacts;

import com.wtest.wtest.articles.Article;
import com.wtest.wtest.articles.responses.ArticleItemListResponse;
import com.wtest.wtest.articles.responses.ArticleResponse;
import com.wtest.wtest.articles.services.ArticlesService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class ArticlesInteract {

    private ArticlesService service;

    public ArticlesInteract(ArticlesService service) {
        this.service = service;
    }

    public Single<ArticleItemListResponse> getArticles() {
        return service.getArticles();
    }

    public List<Article> mapResponseToArticle(ArticleItemListResponse articleItemList) {
        List<ArticleResponse> articleResponseList = articleItemList.getItems();
        List<Article> articlesList = new ArrayList<>();

        for (ArticleResponse response : articleResponseList) {
            articlesList.add(new Article(response.getId(), response.getTitle(),
                    response.getPublishedAt(), response.getHero(), response.getAuthor(),
                    response.getSummary(), response.getBody()));
        }
        return articlesList;
    }
}
