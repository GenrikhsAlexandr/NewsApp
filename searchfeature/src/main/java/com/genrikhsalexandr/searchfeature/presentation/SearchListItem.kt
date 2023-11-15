package com.genrikhsalexandr.searchfeature.presentation

import com.genrikhsaleksandr.core.domain.model.Article

data class SearchListItem(
    val sourceName: String?,
    val title: String,
    val urlToImage: String?,
    val article: Article,
    val id: Long
)