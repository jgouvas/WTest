package com.wtest.wtest.articles.presenters;

import com.wtest.wtest.articles.views.ArticleView;

public class ArticlePresenter {

    private ArticleView view;

    public ArticlePresenter(ArticleView view) {
        this.view = view;
    }

    public void present() {
        view.setupArticleInfo();
    }
}
