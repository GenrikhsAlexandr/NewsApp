package com.genrikhsaleksandr.savefeature.presentation.list

import com.genrikhsaleksandr.core.domain.model.Article

data class NewsItemList(
    val source: String?,
    val title: String,
    val urlToImage: String?,
    val article: Article
)