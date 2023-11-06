package com.genrikhsalexandr.newsapp.data.repository

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.core.domain.model.Sources
import com.genrikhsaleksandr.savefeature.data.NewsDtoMapper
import com.genrikhsaleksandr.savefeature.data.database.ArticleDao
import com.genrikhsalexandr.newsapp.data.network.NewsService
import com.genrikhsalexandr.souresfeature.data.SourcesDtoMapper
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val mapper: NewsDtoMapper,
    private val sourcesMapper: SourcesDtoMapper
) : ArticleRepository {
    companion object {
        private const val BASE_URL = "https://newsapi.org/"
        private val json = Json { ignoreUnknownKeys = true }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(
            OkHttpClient.Builder()
                .apply {
                    addInterceptor(
                        HttpLoggingInterceptor().setLevel(
                            HttpLoggingInterceptor
                                .Level.BODY
                        )
                    )
                }
                .build()
        )
        .build()

    private val service: NewsService = retrofit.create(NewsService::class.java)

    override suspend fun getFavoritesArticle(): List<Article> {
        return articleDao.getArticleFromDb()
            .map {
                mapper.mapArticleDbModelToArticle(it)
            }
    }

    override suspend fun getArticles(): List<Article>? = withContext(Dispatchers.IO) {

        try {
            val response = service.getNews(
                category = "general",
            )
            mapper.mapNewsListDtoToListArticle(response)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getSources(): List<Sources>? = withContext(Dispatchers.IO) {
        try {
            val response = service.getSources()
            sourcesMapper.mapSourcesDtoToSources(response)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun saveFavoritesArticle(article: Article) {
        val articleDbModel = mapper.mapArticleToArticleDbModel(article)
        articleDao.insertArticle(articleDbModel)
    }

    override suspend fun deleteFavoriteArticle(article: Article) {
        val articleDbModel = mapper.mapArticleToArticleDbModel(article)
        articleDao.deleteArticle(articleDbModel)
    }
}