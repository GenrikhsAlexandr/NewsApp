package com.genrikhsaleksandr.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsListDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)