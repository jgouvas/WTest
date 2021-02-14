package com.wtest.wtest.articles;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wtest.wtest.R;
import com.wtest.wtest.articles.fragments.ArticleListFragment;

public class ArticleListActivity extends AppCompatActivity {

    public ArticleListActivity() {
        super(R.layout.articles_activity_layout);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.articles_activity_fragment_container, ArticleListFragment.class, null)
                .commit();
    }

}
