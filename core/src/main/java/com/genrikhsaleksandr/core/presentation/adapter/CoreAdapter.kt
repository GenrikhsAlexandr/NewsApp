package com.genrikhsaleksandr.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsaleksandr.core.R
import com.genrikhsaleksandr.core.databinding.ListItemArticleBinding
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.presentation.ArticleItemList
import com.squareup.picasso.Picasso

class CoreAdapter(
    val isPrimaryBackground:Boolean,
    val onNewsItemClickListener: ((Article) -> Unit)
) : ListAdapter<ArticleItemList, CoreAdapter.NewsViewHolder>(CoreDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsViewHolder(private val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: ArticleItemList) {
            with(binding) {
                nameSource.text = listItem.sourceName
                titleArticle.text = listItem.title
                root.setOnClickListener {
                    onNewsItemClickListener(
                        listItem.article
                    )
                }
                if (isPrimaryBackground){
                    root.setBackgroundResource(R.color.ic_launcher_background)
                } else root.setBackgroundResource(R.color.background_tabs)
            }
            if (listItem.urlToImage != null) {
                Picasso.get()
                    .load(listItem.urlToImage.toString())
                    .into(binding.imageArticle)
            } else binding.imageArticle.setImageResource(R.drawable.element)
        }
    }
}