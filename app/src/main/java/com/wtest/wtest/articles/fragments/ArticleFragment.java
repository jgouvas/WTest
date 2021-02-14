package com.wtest.wtest.articles.fragments;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.appbar.AppBarLayout;
import com.wtest.wtest.R;
import com.wtest.wtest.articles.presenters.ArticlePresenter;
import com.wtest.wtest.articles.views.ArticleView;

import static com.wtest.wtest.articles.fragments.ArticleListFragment.AUTHOR;
import static com.wtest.wtest.articles.fragments.ArticleListFragment.BODY;
import static com.wtest.wtest.articles.fragments.ArticleListFragment.HERO;
import static com.wtest.wtest.articles.fragments.ArticleListFragment.ID;
import static com.wtest.wtest.articles.fragments.ArticleListFragment.PUBLISHED_AT;
import static com.wtest.wtest.articles.fragments.ArticleListFragment.TITLE;

public class ArticleFragment extends Fragment implements ArticleView {

    private int id;
    private String title;
    private String publishedAt;
    private Uri hero;
    private String author;
    private String body;
    private ArticlePresenter presenter;

    private TextView idTextView;
    private TextView titleTextView;
    private TextView publishedAtTextView;
    private ImageView heroImageView;
    private TextView authorTextView;
    private TextView bodyTextView;
    private ConstraintLayout loadingView;
    private ProgressBar imageLoading;
    private AppBarLayout appBarLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.id = getArguments().getInt(ID, -1);
        this.title = getArguments().getString(TITLE, "");
        this.publishedAt = getArguments().getString(PUBLISHED_AT, "");
        this.hero = Uri.parse(getArguments().getString(HERO, ""));
        this.author = getArguments().getString(AUTHOR, "");
        this.body = getArguments().getString(BODY, "");

        return inflater.inflate(R.layout.article_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        idTextView = view.findViewById(R.id.article_id);
        titleTextView = view.findViewById(R.id.article_title);
        publishedAtTextView = view.findViewById(R.id.article_date);
        heroImageView = view.findViewById(R.id.article_hero);
        authorTextView = view.findViewById(R.id.article_author);
        bodyTextView = view.findViewById(R.id.article_body);
        loadingView = view.findViewById(R.id.loading_layout);
        imageLoading = view.findViewById(R.id.image_loading);
        appBarLayout = view.findViewById(R.id.app_bar_layout);


        presenter = new ArticlePresenter(this);
        presenter.present();
    }

    @Override
    public void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @Override
    public void showImageProgressBar() {
        imageLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageProgressBar() {
        imageLoading.setVisibility(View.GONE);
    }

    @Override
    public void setupArticleInfo() {
        setupHero();
        setupTitle();
        setupAuthor();
        setupDate();
        setupId();
        setupBody();
    }

    public void setupHero() {
        if (!hero.toString().equals("")) {
            showImageProgressBar();
            Glide.with(this)
                    .load(hero)
                    .timeout(300000)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            hideImageProgressBar();
                            return false;
                        }
                    })
                    .into(heroImageView);
        } else {
            appBarLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void setupTitle() {
        if (!title.equals("")) {
            titleTextView.setText(title);
        }
    }

    @Override
    public void setupAuthor() {
        if (!author.equals("")) {
            authorTextView.setText(author);
        }
    }

    @Override
    public void setupDate() {
        if (!publishedAt.equals("")) {
            publishedAtTextView.setText(publishedAt);
        }
    }

    @Override
    public void setupId() {
        if (id != -1) {
            idTextView.setText(String.valueOf(id));
        }
    }

    @Override
    public void setupBody() {
        if (!body.equals(""))
            bodyTextView.setText(body);
    }
}
