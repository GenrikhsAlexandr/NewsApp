package com.genrikhsaleksandr.savefeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val source: SourceDto,
    val title: String,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
)