package com.genrikhsaleksandr.core.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsaleksandr.core.R
import com.genrikhsaleksandr.core.databinding.ListItemArticleBinding
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.presentation.ArticleItemList
import com.genrikhsaleksandr.core.presentation.ItemList
import com.squareup.picasso.Picasso

class CoreAdapter(
    val isPrimaryBackground: Boolean,
    val onNewsItemClickListener: ((Article) -> Unit)
) : ListAdapter<ItemList, RecyclerView.ViewHolder>(CoreDiffUtil()) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.list_item_article -> {
                ArticleViewHolder(
                    ListItemArticleBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false
                    )
                )
            }

            R.layout.list_item_loading -> {
                LoadingViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.list_item_loading,
                        parent,
                        false
                    )
                )
            }

            else -> error("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ArticleViewHolder -> {
                holder.bind(getItem(position) as ArticleItemList)
            }

            else -> Unit
        }
    }

    inner class ArticleViewHolder(private val binding: ListItemArticleBinding) :
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
            if (!listItem.urlToImage.isNullOrEmpty()) {
                Picasso.get()
                    .load(listItem.urlToImage.toString())
                    .into(binding.imageArticle)
            } else binding.imageArticle.setImageResource(R.drawable.element)
        }
    }

    inner class LoadingViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
