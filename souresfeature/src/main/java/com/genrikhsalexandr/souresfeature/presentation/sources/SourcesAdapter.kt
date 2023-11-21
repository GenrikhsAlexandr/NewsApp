package com.genrikhsalexandr.souresfeature.presentation.sources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsalexandr.souresfeature.databinding.ListItemSourcesBinding
import java.util.Locale

class SourcesAdapter(
    val onNewsItemClickListener: ((Source) -> Unit)
) : ListAdapter<SourcesItemList, SourcesAdapter.NewsViewHolder>(
    SourceDiffUtil()
) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemSourcesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(private val binding: ListItemSourcesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: SourcesItemList) {
            with(binding) {
                nameSource.text = listItem.name
                category.text = listItem.category?.capitalize(Locale.ROOT)
                country.text = listItem.country
                delimiter.text = " | "
                root.setOnClickListener {
                    onNewsItemClickListener(
                        listItem.source
                    )
                }
            }
        }
    }
}