package com.task.nycnewsarticles.main.network

import com.task.nycnewsarticles.main.model.Articles
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("all-sections/7.json")
    suspend fun getArticles(@Query("api-key") apiKey: String) : Articles
}