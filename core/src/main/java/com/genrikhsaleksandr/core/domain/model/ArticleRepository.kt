package com.genrikhsaleksandr.core.domain.model

interface ArticleRepository {
    suspend fun getFavoritesArticle(): List<Article>

    suspend fun getArticles(): List<Article>?

    suspend fun saveFavoritesArticle(article: Article)

    suspend fun deleteFavoriteArticle(article: Article)

    suspend fun getSources(): List<Source>?

}