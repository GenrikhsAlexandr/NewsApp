package com.genrikhsalexandr.newsapp.data.network

import com.genrikhsaleksandr.core.data.dto.ArticlesListDto
import com.genrikhsalexandr.souresfeature.data.dto.articlessource.SourceNewsListDto
import com.genrikhsalexandr.souresfeature.data.dto.sources.SourcesListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesService {

    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query(QUERY_PARAM_CATEGORY) category: String? = null,
        @Query(QUERY_PARAM_PAGE) page: Int? = null,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "73823f128bf744a6943fd28b9cc7db7c",
    ): ArticlesListDto

    @GET("v2/top-headlines/sources")
    suspend fun getSources(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "73823f128bf744a6943fd28b9cc7db7c",
    ): SourcesListDto

    @GET("v2/top-headlines")
    suspend fun getArticlesSource(
        @Query(QUERY_PARAM_SOURCE) sources: String? = null,
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "73823f128bf744a6943fd28b9cc7db7c",
    ): SourceNewsListDto

    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_CATEGORY = "category"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_SOURCE = "sources"
    }
}
