package com.lorispinna.test.networks;

import com.lorispinna.test.models.Planet;
import com.lorispinna.test.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArticleService {
    @GET("endpoint/")
    Call<List<Planet>> list();

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
