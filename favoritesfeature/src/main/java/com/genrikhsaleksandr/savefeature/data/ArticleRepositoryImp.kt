package com.genrikhsaleksandr.savefeature.data

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class ArticleRepositoryImp(
    private val mapper: NewsDtoMapper = NewsDtoMapper()
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


    /* private val db = Room.databaseBuilder(
         ApplicationContext.appContext!!,
         AppDatabase::class.java, "database-name"
     ).build()
 */

    override suspend fun getFavoritesArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getArticles(): List<Article>? = withContext(Dispatchers.IO) {
        try {
            val response = service.getNews(
                language = "en",
            )
            mapper.mapNewsListDtoToListArticle(response)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun saveFavoritesArticle(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFavoritesArticle(): List<Article> {
        TODO("Not yet implemented")
    }

    /*uspend fun saveArticle(article: ArticleDbModel) {
        db.articleRequestDao().insertArticle(article)
    }

    suspend fun getFavoritesNews(): List<ArticleDbModel> {
        val userDao = db.articleRequestDao()
       return userDao.getArticleFromDb()
    }
    suspend fun deleteArticle(article: ArticleDbModel) {
       db.articleRequestDao().deleteArticle(article)
   }*/
}