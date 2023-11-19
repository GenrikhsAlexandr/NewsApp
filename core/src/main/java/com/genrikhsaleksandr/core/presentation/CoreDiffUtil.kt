package com.genrikhsaleksandr.core.presentation

import androidx.recyclerview.widget.DiffUtil

class CoreDiffUtil : DiffUtil.ItemCallback<ArticleItemList>() {

    override fun areItemsTheSame(oldItem: ArticleItemList, newItem: ArticleItemList): Boolean {
        return oldItem.article.url ==newItem.article.url
    }

    override fun areContentsTheSame(oldItem: ArticleItemList, newItem: ArticleItemList): Boolean {
        return oldItem==newItem    }
}