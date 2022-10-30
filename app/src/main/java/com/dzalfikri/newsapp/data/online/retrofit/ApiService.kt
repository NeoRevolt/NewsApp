package com.dzalfikri.newsapp.data.online.retrofit

import com.dzalfikri.newsapp.data.online.responses.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=id")
    fun getNews(
        @Query("apiKey")
        apiKey: String
    ): Call<NewsResponse>
}