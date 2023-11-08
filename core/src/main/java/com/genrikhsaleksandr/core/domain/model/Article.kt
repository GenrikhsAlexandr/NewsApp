package com.genrikhsaleksandr.core.domain.model

import java.io.Serializable

data class Article(
    val id:Long = 0,
    val sourceId:String?,
    val sourceName: String?,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val url: String
):Serializable