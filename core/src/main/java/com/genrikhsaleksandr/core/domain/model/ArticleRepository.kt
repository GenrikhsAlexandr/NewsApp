package com.genrikhsaleksandr.core.domain.model

import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    suspend fun getFavoritesArticle(article: Article): List<Article>

    suspend fun getArticles(): List<Article>?

    suspend fun saveFavoritesArticle(article: Article)

    suspend fun deleteFavoritesArticle(article: Article)

}