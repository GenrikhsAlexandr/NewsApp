package com.genrikhsaleksandr.savefeature.data

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val source: SourceDto,
    val author: String? = null,
    val title: String,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null
)