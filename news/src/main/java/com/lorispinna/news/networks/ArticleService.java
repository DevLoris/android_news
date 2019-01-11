package com.lorispinna.news.networks;

import com.lorispinna.news.models.ArticleList;
import com.lorispinna.news.utils.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArticleService {
    @GET("everything")
    Call<ArticleList> list(@Query("q") String query, @Query("apiKey") String apiKey);

}
