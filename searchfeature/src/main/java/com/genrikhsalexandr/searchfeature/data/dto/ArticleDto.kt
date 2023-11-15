package com.genrikhsalexandr.searchfeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto (
    val source: SourceDto,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val url:String
)