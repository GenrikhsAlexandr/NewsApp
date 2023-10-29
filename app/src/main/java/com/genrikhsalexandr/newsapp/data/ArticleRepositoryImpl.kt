package com.genrikhsalexandr.newsapp.data

import android.app.Application
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.savefeature.data.NewsDtoMapper
import com.genrikhsaleksandr.savefeature.data.NewsService
import com.genrikhsaleksandr.savefeature.data.database.AppDatabase
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

class ArticleRepositoryImpl @Inject constructor  (
    application: Application,
    private val mapper: NewsDtoMapper = NewsDtoMapper()
) : ArticleRepository {

    private val articleDao = AppDatabase.getInstance(application).articleRequestDao()

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

    override suspend fun getArticles(): List<Article>? = withContext(Dispatchers.IO) {

        try {
            val response = service.getNews(
                category = "business",
            )
            mapper.mapNewsListDtoToListArticle(response)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getFavoritesArticle(article: Article): List<Article> {
        return articleDao.getArticleFromDb()
            .map {
                mapper.mapArticleDbModelToArticle(it)
            }
    }

    override suspend fun saveFavoritesArticle(article: Article) {
        val articleDbModel = mapper.mapArticleToArticleDbModel(article)
        articleDao.insertArticle(articleDbModel)
    }

    override suspend fun deleteFavoritesArticle(article: Article) {
        val articleDbModel = mapper.mapArticleToArticleDbModel(article)
        articleDao.deleteArticle(articleDbModel)
    }
}