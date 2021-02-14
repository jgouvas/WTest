package com.wtest.wtest.articles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wtest.wtest.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class ArticleRecyclerViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private List<Article> articleList;
    private PublishSubject<Article> articleClickSubject = PublishSubject.create();
    private Observable<Article> articleClickEvent = articleClickSubject;


    public ArticleRecyclerViewAdapter(List<Article> articleList) {
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.getTitle().setText(articleList.get(position).getTitle());
        holder.getAuthor().setText(articleList.get(position).getAuthor());
        if (articleList.get(position).getSummary() != null) {
            holder.getSummary().setVisibility(View.VISIBLE);
            holder.getSummary().setText(articleList.get(position).getSummary());
        }

        holder.getItemLayout().setOnClickListener(view -> articleClickSubject.onNext(articleList.get(position)));
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setArticles(ArrayList<Article> articleList) {
        this.articleList = articleList;
    }

    public Observable<Article> getArticleClickEvent() {
        return articleClickEvent;
    }
}
