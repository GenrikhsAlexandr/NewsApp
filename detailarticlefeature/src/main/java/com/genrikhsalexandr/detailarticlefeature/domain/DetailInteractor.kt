package com.genrikhsalexandr.detailarticlefeature.domain

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import javax.inject.Inject

class DetailInteractor @Inject constructor(
    private val repository: ArticleRepository
) {

   // suspend fun isFavorite(article: Article) = repository.isFavorite(article)
    suspend fun saveFavoritesArticle(article: Article) = repository.saveFavoriteArticle(article)

    suspend fun deleteFavoriteArticle(article: Article) = repository.deleteFavoriteArticle(article)
}