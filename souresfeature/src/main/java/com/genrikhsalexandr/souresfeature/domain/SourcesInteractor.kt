package com.genrikhsalexandr.souresfeature.domain

import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.core.domain.model.Sources
import javax.inject.Inject

class SourcesInteractor @Inject constructor(
    private val sourcesRepository: ArticleRepository
) {
    suspend fun getSourcesList(): List <Sources>? = sourcesRepository.getSources()
}