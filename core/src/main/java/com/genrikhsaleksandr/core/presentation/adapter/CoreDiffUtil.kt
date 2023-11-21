package com.genrikhsaleksandr.core.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.genrikhsaleksandr.core.presentation.ArticleItemList

class CoreDiffUtil : DiffUtil.ItemCallback<ArticleItemList>() {

    override fun areItemsTheSame(oldItem: ArticleItemList, newItem: ArticleItemList): Boolean {
        return oldItem.article.url == newItem.article.url
    }

    override fun areContentsTheSame(oldItem: ArticleItemList, newItem: ArticleItemList): Boolean {
        return oldItem == newItem
    }
}
