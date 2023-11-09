package com.genrikhsaleksandr.core.domain.model

interface ArticleRepository {
    suspend fun getFavoritesArticle(): List<Article>

    suspend fun getArticles(): List<Article>?

    suspend fun saveFavoriteArticle(article: Article):Article

    suspend fun deleteFavoriteArticle(article: Article)

    suspend fun getSources(): List<Source>?

    suspend fun getArticlesSource(id:String?): List<Article>?

    suspend fun isFavorite(article:Article): Boolean
}