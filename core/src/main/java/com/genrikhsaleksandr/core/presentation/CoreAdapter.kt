package com.genrikhsaleksandr.core.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsaleksandr.core.databinding.ListItemArticleBinding
import com.genrikhsaleksandr.core.domain.model.Article
import com.squareup.picasso.Picasso

class CoreAdapter(
    val onNewsItemClickListener: ((Article)->Unit)
) : RecyclerView.Adapter<CoreAdapter.NewsViewHolder>() {

    private var list: List<ArticleItemList> = ArrayList()

    fun submitData(list: List<ArticleItemList>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemArticleBinding.inflate(
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
            }
            if (listItem.urlToImage != null) {
                Picasso.get()
                    .load(listItem.urlToImage.toString())
                    .into(binding.imageArticle)
            }  else binding.imageArticle.setImageResource(com.genrikhsaleksandr.core.R.drawable.element)
        }
    }
}