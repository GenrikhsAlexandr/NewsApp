package com.genrikhsaleksandr.savefeature.domain

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoritesInteractor @Inject constructor(
    private val repository: ArticleRepository,
) {

    fun getFavoritesArticles(): Flow<List<Article>> = repository.getFavoritesArticle()
}
