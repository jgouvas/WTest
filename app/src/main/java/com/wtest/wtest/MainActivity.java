package com.wtest.wtest;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding2.view.RxView;
import com.wtest.wtest.articles.ArticleListActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;


public class MainActivity extends AppCompatActivity {

    private Button articlesButton;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        articlesButton = findViewById(R.id.navigate_articles_button);

        handleArticlesButtonClicks();
    }

    private void navigateToArticlesActivity() {
        Intent intent = new Intent(this, ArticleListActivity.class);
        startActivity(intent);
    }

    private void handleArticlesButtonClicks() {
        disposables.add(articleButtonClicks()
                .throttleFirst(1, TimeUnit.SECONDS)
                .doOnNext(__ -> navigateToArticlesActivity())
                .subscribe());
    }

    private Observable<Object> articleButtonClicks() {
        return RxView.clicks(articlesButton);
    }
}