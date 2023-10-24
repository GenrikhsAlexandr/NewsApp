package com.genrikhsaleksandr.savefeature.domain

import retrofit2.http.Url

data class NewsItemList(
    val sourceId: String?=null,
    val sourceName:String,
    val title: String,
    val url: String? = null,
    val urlToImage: Url? = null,
)
