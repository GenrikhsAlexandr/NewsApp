package com.genrikhsaleksandr.savefeature.domain

import retrofit2.http.Url

data class NewsItemList(
    val sourceName:String,
    val title: String,
    val urlToImage: Url? = null,
    val news:News
)
