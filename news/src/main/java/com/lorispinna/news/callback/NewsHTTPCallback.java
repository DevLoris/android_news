package com.lorispinna.news.callback;

import com.lorispinna.news.R;
import com.lorispinna.news.fragments.NewsListFragment;
import com.lorispinna.news.models.ArticleList;
import com.lorispinna.news.utils.Callback;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Response;

public abstract class NewsHTTPCallback implements Callback {
    public NewsHTTPCallback() {
    }

     public abstract void call(Response<ArticleList> list);

    @Override
    public void call() {
    }
}
