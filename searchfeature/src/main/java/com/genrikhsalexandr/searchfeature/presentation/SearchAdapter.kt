package com.genrikhsalexandr.searchfeature.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsalexandr.searchfeature.databinding.ListItemSearchBinding
import com.squareup.picasso.Picasso

class SearchAdapter(
    val onNewsItemClickListener: ((Article) -> Unit)
) : RecyclerView.Adapter<SearchAdapter.NewsViewHolder>() {

    private lateinit var list: List<SearchListItem>

    fun submitData(list: List<SearchListItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemSearchBinding.inflate(
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

    inner class NewsViewHolder(private val binding: ListItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: SearchListItem) {
            with(binding) {
                nameSource.text = listItem.sourceName
                title.text = listItem.title
                root.setOnClickListener {
                    onNewsItemClickListener(
                        listItem.article
                    )
                }
            }
            if (listItem.urlToImage != null) {
                Picasso.get()
                    .load(listItem.urlToImage.toString())
                    .into(binding.imageNews)
            } else binding.imageNews.setImageResource(com.genrikhsaleksandr.core.R.drawable.element)
        }
    }
}