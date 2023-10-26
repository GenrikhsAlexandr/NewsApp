package com.genrikhsaleksandr.savefeature.presentation

import com.genrikhsaleksandr.savefeature.domain.News

data class NewsItemList(
    val author:String?,
    val title: String,
    val urlToImage: String?,
    val news: News
)
