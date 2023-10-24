package com.genrikhsaleksandr.savefeature.data

import androidx.room.Room
import com.genrikhsaleksandr.savefeature.data.database.AppDatabase
import com.genrikhsaleksandr.savefeature.data.database.NewsDbModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NewsRepository {

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


    private val db = Room.databaseBuilder(
        ApplicationContext.appContext!!,
        AppDatabase::class.java, "database-name"
    ).build()


    suspend fun saveNews(news: NewsDbModel) {
        db.newsRequestDao().insertNews(news)
    }

    suspend fun getNews(): Flow<List<NewsDbModel>> {
        val userDao = db.newsRequestDao()
        return userDao.getNewsFromDb()
    }

    suspend fun deleteNews(news: NewsDbModel) {
        db.newsRequestDao().deleteNews(news)
    }
}