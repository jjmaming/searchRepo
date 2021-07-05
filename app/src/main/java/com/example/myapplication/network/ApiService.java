package com.example.myapplication.network;

import android.database.Observable;

import com.example.myapplication.model.GithubSearchModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {

    @Headers({"Accept: application/vnd.github.v3+json"})
    @GET("search/repositories")
    Call<GithubSearchModel> searchGithubRepo(@Query("q") String searchParam);
}
