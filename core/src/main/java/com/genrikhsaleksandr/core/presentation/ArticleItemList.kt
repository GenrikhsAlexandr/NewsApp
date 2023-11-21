package com.genrikhsaleksandr.core.presentation

import com.genrikhsaleksandr.core.R
import com.genrikhsaleksandr.core.domain.model.Article

data class ArticleItemList(
    val sourceName: String?,
    val title: String,
    val urlToImage: String?,
    val article: Article,
    val id: Long
) {
    val viewType = R.layout.list_item_article
}