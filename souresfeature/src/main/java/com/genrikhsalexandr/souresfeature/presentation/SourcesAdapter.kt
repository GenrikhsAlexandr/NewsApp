package com.genrikhsalexandr.souresfeature.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsaleksandr.core.domain.model.Sources
import com.genrikhsalexandr.souresfeature.databinding.ListItemSourcesBinding
import java.util.Locale

class SourcesAdapter(
    val onNewsItemClickListener: ((Sources) -> Unit)
) : RecyclerView.Adapter<SourcesAdapter.NewsViewHolder>() {

    private lateinit var list: List<SourcesItemList>

    fun submitData(list: List<SourcesItemList>) {
        this.list = list
        notifyDataSetChanged()
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

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(list[position])
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
                        listItem.articleSources
                    )
                }
            }
        }
    }
}