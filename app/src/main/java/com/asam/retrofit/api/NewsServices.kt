package com.asam.retrofit.api

import com.asam.retrofit.models.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServices {

    @GET("top-headlines")
    fun getNews(
            @Query("country")
            country: String,
            @Query("apiKey")
            apiKey: String
    ) : Call<NewsResponse>

}