package com.genrikhsaleksandr.savefeature.domain

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import javax.inject.Inject

class FavoritesInteractor @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend fun getFavoritesArticles(): List<Article> = repository.getFavoritesArticle()

    suspend fun getArticlesListForCategory(category: String): List<Article>? =
        repository.getArticlesForCategory(
            category
        )

    suspend fun saveFavoritesArticle(article: Article): Article =
        repository.saveFavoriteArticle(article)

    suspend fun deleteFavoriteArticle(article: Article) = repository.deleteFavoriteArticle(article)
}