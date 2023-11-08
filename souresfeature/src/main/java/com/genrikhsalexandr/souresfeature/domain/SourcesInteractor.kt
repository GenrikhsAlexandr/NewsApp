package com.genrikhsalexandr.souresfeature.domain

import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.core.domain.model.Source
import javax.inject.Inject

class SourcesInteractor @Inject constructor(
    private val sourcesRepository: ArticleRepository
) {
    suspend fun getSourcesList(): List<Source>? = sourcesRepository.getSources()

    suspend fun getArticlesSourceList(articlesSource: Article): List<Article>? =
        sourcesRepository.getArticlesSource(articlesSource.sourceId)
}