package com.genrikhsaleksandr.core.domain.model

import com.genrikhsaleksandr.core.R
import java.io.Serializable

data class Article(
    val sourceId: String?,
    val sourceName: String?,
    val title: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?,
    val url: String,
    val id: Long = 0,
) : Serializable {
    val viewType = R.layout.list_item_article
}