package com.genrikhsalexandr.souresfeature.presentation.articlessource

import com.genrikhsalexandr.souresfeature.domain.ArticlesSource

data class ArticlesSourceItemList(
    val source: String?,
    val title: String,
    val urlToImage: String?,
    val articlesSource: ArticlesSource
    )
