package com.genrikhsaleksandr.savefeature.domain

import retrofit2.http.Url

data class News(
    val author:String?,
    val title: String,
    val urlToImage: String?,
)