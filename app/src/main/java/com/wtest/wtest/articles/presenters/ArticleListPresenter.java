package com.wtest.wtest.articles.presenters;

import androidx.recyclerview.widget.RecyclerView;

import com.wtest.wtest.articles.ArticleRecyclerViewAdapter;
import com.wtest.wtest.articles.fragments.ArticleListFragment;
import com.wtest.wtest.articles.interacts.ArticlesInteract;
import com.wtest.wtest.articles.views.ArticleListView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public class ArticleListPresenter {

    private ArticleListView view;
    private ArticleRecyclerViewAdapter articleViewAdapter;
    private Scheduler networkScheduler;
    private Scheduler viewScheduler;
    private ArticlesInteract interact;
    private CompositeDisposable disposables;

    public ArticleListPresenter(ArticleListFragment view, Scheduler viewScheduler,
                                Scheduler networkScheduler, CompositeDisposable disposables,
                                ArticlesInteract interact) {
        this.view = view;
        this.viewScheduler = viewScheduler;
        this.networkScheduler = networkScheduler;
        this.interact = interact;
        this.disposables = disposables;
    }

    public void present() {
        handleArticleFetch();
    }

    private void handleArticleFetch() {
        disposables.add(interact.getArticles()
                .subscribeOn(networkScheduler)
                .observeOn(viewScheduler)
                .doOnSubscribe(__ -> view.showLoading())
                .doOnSuccess(articleList -> {
                    articleViewAdapter =
                            new ArticleRecyclerViewAdapter(interact.mapResponseToArticle(articleList));
                    articleViewAdapter.setStateRestorationPolicy(
                            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY);
                    view.setupRecyclerView(articleViewAdapter);
                    view.hideLoading();
                })
                .doOnSuccess(__ -> handleArticleListClick())
                .subscribe());
    }

    private void handleArticleListClick() {
        disposables.add(articleViewAdapter.getArticleClickEvent()
                .subscribeOn(viewScheduler)
                .observeOn(viewScheduler)
                .throttleFirst(1, TimeUnit.SECONDS)
                .doOnNext(article -> view.navigateToArticleView(article))
                .subscribe());
    }
}
