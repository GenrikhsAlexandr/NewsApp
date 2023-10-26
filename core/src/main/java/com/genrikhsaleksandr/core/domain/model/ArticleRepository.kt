package com.genrikhsaleksandr.core.domain.model

interface ArticleRepository {
    suspend fun getFavoritesArticle(article: Article)

    suspend fun getArticles(): List<Article>?

    suspend fun saveFavoritesArticle(article: Article)

    suspend fun deleteFavoritesArticle(): List<Article>

}