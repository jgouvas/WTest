package com.wtest.wtest.articles.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wtest.wtest.R;
import com.wtest.wtest.articles.Article;
import com.wtest.wtest.articles.presenters.ArticleListPresenter;
import com.wtest.wtest.articles.ArticleRecyclerViewAdapter;
import com.wtest.wtest.articles.interacts.ArticlesInteract;
import com.wtest.wtest.articles.services.ArticlesService;
import com.wtest.wtest.articles.views.ArticleListView;
import com.wtest.wtest.utils.DateUtils;


import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ArticleListFragment extends Fragment implements ArticleListView {

    public static String ID = "id";
    public static String TITLE = "title";
    public static String PUBLISHED_AT = "published_at";
    public static String HERO = "hero";
    public static String AUTHOR = "author";
    public static String BODY = "body";

    private ArticleListPresenter presenter;
    private RecyclerView articlesRecyclerView;
    private ArticleRecyclerViewAdapter articleViewAdapter;
    private ConstraintLayout loadingLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.articles_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        articlesRecyclerView = view.findViewById(R.id.articles_list_view);
        loadingLayout = view.findViewById(R.id.loading_layout);

        presenter = new ArticleListPresenter(this, AndroidSchedulers.mainThread(),
                Schedulers.io(), new CompositeDisposable(),
                new ArticlesInteract(new ArticlesService()));

        presenter.present();
    }

    @Override
    public void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    public void setupRecyclerView(ArticleRecyclerViewAdapter articleViewAdapter) {
        articlesRecyclerView.setAdapter(articleViewAdapter);
        articlesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void navigateToArticleView(Article article) {
        Bundle bundle = new Bundle();
        bundle.putInt(ID, article.getId());
        bundle.putString(TITLE, article.getTitle());
        bundle.putString(PUBLISHED_AT, DateUtils.dateToString(article.getPublishedAt()));
        bundle.putString(HERO, article.getHero());
        bundle.putString(AUTHOR, article.getAuthor());
        bundle.putString(BODY, article.getBody());

        Fragment articleFragment = new ArticleFragment();
        articleFragment.setArguments(bundle);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.articles_activity_fragment_container, articleFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
