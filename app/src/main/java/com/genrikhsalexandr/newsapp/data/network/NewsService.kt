package com.genrikhsalexandr.newsapp.data.network

import com.genrikhsaleksandr.savefeature.data.dto.NewsListDto
import com.genrikhsalexandr.souresfeature.data.dto.articlessource.SourceNewsListDto
import com.genrikhsalexandr.souresfeature.data.dto.sources.SourcesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "c671c3a213884080b9986d20a5ba091a",
        @Query(QUERY_PARAM_CATEGORY) category: String,
    ): NewsListDto

    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "c671c3a213884080b9986d20a5ba091a",
    ): SourcesListDto

    @GET("v2/top-headlines")
    suspend fun getNewsSource(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "c671c3a213884080b9986d20a5ba091a",
        @Query(QUERY_PARAM_SOURCE) source: String,
    ): SourceNewsListDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_CATEGORY = "category"
        private const val QUERY_PARAM_SOURCE = "source"
    }
}