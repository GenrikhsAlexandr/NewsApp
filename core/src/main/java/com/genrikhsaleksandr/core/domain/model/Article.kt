package com.genrikhsaleksandr.core.domain.model

data class Article(
    val author: String?,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)