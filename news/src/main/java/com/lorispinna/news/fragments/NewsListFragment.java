package com.lorispinna.news.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lorispinna.news.R;
import com.lorispinna.news.adapters.NewsListRecyclerAdapter;
import com.lorispinna.news.listeners.NewsDetailsClickListener;
import com.lorispinna.news.listeners.ShareListener;
import com.lorispinna.news.models.Article;
import com.lorispinna.news.utils.Api;
import com.lorispinna.news.utils.ArticleSingleton;
import com.lorispinna.news.utils.DatabaseListSingleton;
import com.lorispinna.news.viewmodels.ArticleViewModel;

import java.util.List;
import java.util.concurrent.Callable;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bolts.Task;

public class NewsListFragment extends CommonFragment implements NewsDetailsClickListener {
    private RecyclerView recyclerView;
    private ArticleViewModel model;
    private NewsListRecyclerAdapter newsListRecyclerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = ViewModelProviders.of(getActivity()).get(ArticleViewModel.class);
        newsListRecyclerAdapter = new NewsListRecyclerAdapter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_news_list, container, false);

        this.recyclerView = view.findViewById(R.id.news_list_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(newsListRecyclerAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        model.getArticles().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                newsListRecyclerAdapter.setArticles(articles);
                newsListRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(Article article) {
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        FragmentTransaction transaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_up, 0, 0, R.anim.slide_down);

        transaction.add(R.id.app_content, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

        model.getSelectedArticle().postValue(article);
    }

    @Override
    public void onShareClick(Article article) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, article.getTitle());
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, article.getUrl());
        getContext().startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    @Override
    public void onLikeListener(final Article article, final Integer integer) {
        Task.callInBackground(new Callable<Void>() {
            @Override
            public Void call() {
                article.setFavorite(!article.isFavorite());
                DatabaseListSingleton.getInstance().getArticleDao().update(article);

                return null;
            }
        });
    }
}
