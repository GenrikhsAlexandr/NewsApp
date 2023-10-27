package com.genrikhsaleksandr.savefeature.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.savefeature.databinding.ListItemNewsBinding
import com.squareup.picasso.Picasso

class FavoritesAdapter(
    val onNewsItemClickListener: ((Article) -> Unit)
) : RecyclerView.Adapter<FavoritesAdapter.NewsViewHolder>() {

    private lateinit var list: List<NewsItemList>

    fun submitData(list: List<NewsItemList>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemNewsBinding.inflate(
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

    inner class NewsViewHolder(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: NewsItemList) {
            if (listItem.urlToImage != null) {
                Picasso.get()
                    .load(listItem.urlToImage.toString())
                    .into(binding.imageNews)
                with(binding) {
                    nameSource.text = listItem.source
                    title.text = listItem.title
                    root.setOnClickListener {
                        onNewsItemClickListener(
                            listItem.article)
                    }
                }
            }
        }
    }
}
