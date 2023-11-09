package com.genrikhsalexandr.souresfeature.presentation.articlessource

import com.genrikhsaleksandr.core.domain.model.Article

data class ArticlesSourceItemList(
    val sourceName: String?,
    val title: String?,
    val articlesSource: Article,
    val urlToImage: String?,
)