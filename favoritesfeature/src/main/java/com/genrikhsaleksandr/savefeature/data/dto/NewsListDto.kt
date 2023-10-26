package com.genrikhsaleksandr.savefeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsListDto(
    val author: String?,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
)