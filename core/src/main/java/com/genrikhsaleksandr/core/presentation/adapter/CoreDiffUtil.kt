package com.genrikhsaleksandr.core.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.genrikhsaleksandr.core.presentation.ItemList

class CoreDiffUtil : DiffUtil.ItemCallback<ItemList>() {

    override fun areItemsTheSame(oldItem: ItemList, newItem: ItemList): Boolean {
        return oldItem.recyclerId == newItem.recyclerId
    }

    override fun areContentsTheSame(oldItem: ItemList, newItem: ItemList): Boolean {
        return oldItem == newItem
    }
}
