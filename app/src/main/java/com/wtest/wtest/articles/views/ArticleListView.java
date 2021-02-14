package com.wtest.wtest.articles.views;

import com.wtest.wtest.articles.Article;
import com.wtest.wtest.articles.ArticleRecyclerViewAdapter;

import java.util.List;

public interface ArticleListView {

    void showLoading();
    void hideLoading();
    void setupRecyclerView(ArticleRecyclerViewAdapter articleViewAdapter);
    void navigateToArticleView(Article article);
}
