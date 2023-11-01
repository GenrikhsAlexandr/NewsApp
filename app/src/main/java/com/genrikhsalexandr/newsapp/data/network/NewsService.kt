package com.genrikhsalexandr.newsapp.data.network

import com.genrikhsaleksandr.savefeature.data.dto.NewsListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "c671c3a213884080b9986d20a5ba091a",
        @Query(QUERY_PARAM_CATEGORY) category: String,
    ): NewsListDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_CATEGORY = "category"
    }
}