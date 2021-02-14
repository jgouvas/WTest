package com.wtest.wtest.articles;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.wtest.wtest.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {

    private ConstraintLayout itemLayout;
    private TextView title;
    private TextView author;
    private TextView summary;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.article_title_view);
        author = itemView.findViewById(R.id.article_author_view);
        summary = itemView.findViewById(R.id.article_summary_view);
        itemLayout = itemView.findViewById(R.id.article_list_row);
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getAuthor() {
        return author;
    }

    public TextView getSummary() {
        return summary;
    }

    public ConstraintLayout getItemLayout() {
        return itemLayout;
    }
}
