package com.genrikhsaleksandr.core.domain.model

import com.genrikhsaleksandr.core.domain.Category

interface ArticleRepository {
    suspend fun getFavoritesArticle(): List<Article>

    suspend fun getArticlesForCategory(category:String): List<Article>?

    fun getArticlesForCategoryBlocking(category: Category): List<Article>?

    suspend fun getArticlesForQuery(query:String): List<Article>?

    suspend fun saveFavoriteArticle(article: Article): Article

    suspend fun deleteFavoriteArticle(article: Article)

    suspend fun getSources(): List<Source>?

    suspend fun getArticlesSource(articlesSourceId: String?): List<Article>?

    suspend fun isFavorite(article: Article): Boolean
}