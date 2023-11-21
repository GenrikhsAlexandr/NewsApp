package com.genrikhsaleksandr.core.presentation

import com.genrikhsaleksandr.core.R
import com.genrikhsaleksandr.core.domain.model.Article
import java.util.UUID

sealed interface ItemList {
    val viewType: Int
    val recyclerId: String
}

data object LoadingItemList : ItemList {

    override val viewType: Int = R.layout.list_item_loading

    override val recyclerId: String = UUID.randomUUID().toString()
}

data class ArticleItemList(
    val sourceName: String?,
    val title: String,
    val urlToImage: String?,
    val article: Article,
    val id: Long
) : ItemList {
    override val viewType: Int = R.layout.list_item_article

    override val recyclerId: String = article.url
}