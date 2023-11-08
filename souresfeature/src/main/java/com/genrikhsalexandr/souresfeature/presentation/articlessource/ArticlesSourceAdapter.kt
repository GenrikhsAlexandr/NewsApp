package com.genrikhsalexandr.sourcesfeature.presentation.ArticlesSource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genrikhsalexandr.souresfeature.databinding.ListItemSourceArticlesBinding
import com.genrikhsalexandr.souresfeature.presentation.articlessource.ArticlesSourceItemList

class ArticlesSourceAdapter(
   /* val onNewsItemClickListener: ((Article) -> Unit)*/
) : RecyclerView.Adapter<ArticlesSourceAdapter.NewsViewHolder>() {

    private lateinit var list: List<ArticlesSourceItemList>

    fun submitData(list: List<ArticlesSourceItemList>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ListItemSourceArticlesBinding.inflate(
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

    inner class NewsViewHolder(private val binding: ListItemSourceArticlesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listItem: ArticlesSourceItemList) {
            with(binding) {
                nameSource.text = listItem.source
                title.text = listItem.title
            }
        }
    }
}