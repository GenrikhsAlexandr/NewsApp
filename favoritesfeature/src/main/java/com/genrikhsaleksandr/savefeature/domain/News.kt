package com.genrikhsaleksandr.savefeature.domain

import retrofit2.http.Url

data class News(
    val sourceId: String?=null,
    val sourceName:String,
    val title: String,
    val url: String? = null,
    val pageSize:Int,
    val urlToImage: Url? = null,
)