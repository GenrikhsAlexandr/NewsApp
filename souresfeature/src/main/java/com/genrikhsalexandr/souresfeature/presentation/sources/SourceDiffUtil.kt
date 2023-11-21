package com.genrikhsalexandr.souresfeature.presentation.sources

import androidx.recyclerview.widget.DiffUtil

class SourceDiffUtil : DiffUtil.ItemCallback<SourcesItemList>() {

    override fun areItemsTheSame(oldItem: SourcesItemList, newItem: SourcesItemList): Boolean {
        return oldItem.source.sourceId == newItem.source.sourceId
    }

    override fun areContentsTheSame(oldItem: SourcesItemList, newItem: SourcesItemList): Boolean {
        return oldItem == newItem
    }
}