package com.genrikhsalexandr.searchfeature.domain

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val repository: ArticleRepository
) {
    suspend fun getArticlesListForSearch(q: String): List<Article>? =
        repository.getArticlesForQuery(
            query = q
        )
}