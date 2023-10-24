package com.genrikhsaleksandr.savefeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    val status: String,
    val totalResults: String,
    val pageSize:Int,
    val articles: List<ArticleDto>
)