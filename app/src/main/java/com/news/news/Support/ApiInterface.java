package com.news.news.Support;

import com.news.news.Models.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("everything?q=bitcoin&from=2020-07-12&sortBy=publishedAt&apiKey=1b05315dcaa94b988fd244fca7a22110")
    Call<Example> callExmpApi();
}
