package com.lorispinna.news.utils;

import android.util.Log;

import com.lorispinna.news.callback.NewsHTTPCallback;
import com.lorispinna.news.models.Article;
import com.lorispinna.news.models.ArticleList;
import com.lorispinna.news.networks.ArticleService;

import java.util.concurrent.Callable;

import bolts.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static final String API_KEY = "be1958d39b4d4932837558f1e1393739";

    public static void getNews(String query, final NewsHTTPCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ArticleService service = retrofit.create(ArticleService.class);
        Call<ArticleList> articleListCall = service.list(query, Api.API_KEY);
        articleListCall.enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, final Response<ArticleList> response) {
                callback.call(response);
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                Log.d("NEWS QUERY", "onResponse: " + t.getLocalizedMessage());
            }
        });
    }
}
