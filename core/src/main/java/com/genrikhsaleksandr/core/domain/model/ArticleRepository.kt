package com.genrikhsaleksandr.core.domain.model

import com.genrikhsaleksandr.core.domain.Category
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getFavoritesArticle(): Flow<List<Article>>

    suspend fun getArticlesForCategory(page: Int, category: String): List<Article>?

    fun getArticlesForCategoryBlocking(page: Int, category: Category): List<Article>?

    suspend fun saveFavoriteArticle(article: Article): Article

    suspend fun deleteFavoriteArticle(article: Article)

    suspend fun getSources(): List<Source>?

    suspend fun getArticlesSource(articlesSourceId: String?): List<Article>?

    suspend fun isFavorite(article: Article): Boolean
}
