package com.genrikhsalexandr.souresfeature.data.dto.articlessource

import kotlinx.serialization.Serializable

@Serializable
data class ArticlesDto(
    val source: SourceArticlesDto,
    val title: String,
    val publishedAt: String,
    val url: String
)
