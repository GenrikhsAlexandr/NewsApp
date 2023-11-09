package com.genrikhsaleksandr.savefeature.presentation.list

import com.genrikhsaleksandr.core.domain.model.Article

data class NewsItemList(
    val sourceName: String?,
    val title: String,
    val urlToImage: String?,
    val article: Article,
    val id:Long
)