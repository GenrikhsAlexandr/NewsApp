package com.genrikhsalexandr.searchfeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchListDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)
