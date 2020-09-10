package com.mini.review.service;

import com.mini.review.model.Product;
import com.mini.review.model.SearchKeyword;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {
    @GET("searchKeyword")
    Call<List<SearchKeyword>> callKeywords();

    @GET("product/{keyword}")
    Call<List<Product>> callProducts(@Path("keyword") String keyword);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.21:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
