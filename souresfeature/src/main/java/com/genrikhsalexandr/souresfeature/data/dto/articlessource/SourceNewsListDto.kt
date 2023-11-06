package com.genrikhsalexandr.souresfeature.data.dto.articlessource

import kotlinx.serialization.Serializable

@Serializable
data class SourceNewsListDto(
    val articles: List<ArticlesDto>,
    val status: String,
    val totalResults: Int
)
