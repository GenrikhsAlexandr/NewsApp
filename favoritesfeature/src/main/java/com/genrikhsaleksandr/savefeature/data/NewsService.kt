package com.genrikhsaleksandr.savefeature.data

import com.genrikhsaleksandr.savefeature.data.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "c671c3a213884080b9986d20a5ba091a",
        @Query(QUERY_PARAM_LANGUAGE) language: String,
        @Query(QUERY_PARAM_CATEGORY) category: String,
        @Query(QUERY_PARAM_TO_SOURCES) sources: String,
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: Int,
    ): NewsDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_LANGUAGE= "language"
        private const val QUERY_PARAM_CATEGORY = "category"
        private const val QUERY_PARAM_TO_SOURCES = "sources"
        private const val QUERY_PARAM_PAGE_SIZE = "sources"

    }
}