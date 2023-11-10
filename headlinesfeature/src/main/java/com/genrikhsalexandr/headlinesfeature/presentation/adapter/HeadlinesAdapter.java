package com.genrikhsalexandr.headlinesfeature.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsalexandr.headlinesfeature.databinding.ListItemArticleBinding;
import com.genrikhsalexandr.headlinesfeature.presentation.ArticleItemList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.ArticleViewHolder> {
    private List<ArticleItemList> list;
    private final OnArticleItemClickListener onArticleItemClickListener;

    public HeadlinesAdapter(OnArticleItemClickListener onArticleItemClickListener) {
        this.onArticleItemClickListener = onArticleItemClickListener;
    }

    public void submitData(List<ArticleItemList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArticleViewHolder(
                ListItemArticleBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final ListItemArticleBinding binding;

        public ArticleViewHolder(ListItemArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ArticleItemList listItem) {
            binding.nameSource.setText(listItem.getSourceName());
            binding.titleArticle.setText(listItem.getTitle());
            binding.getRoot().setOnClickListener(v -> onArticleItemClickListener.onItemClick(listItem.getArticle()));

            if (listItem.getUrlToImage() != null) {
                Picasso.get()
                        .load(listItem.getUrlToImage().toString())
                        .into(binding.imageArticle);
            }
        }
    }

    public interface OnArticleItemClickListener {
        void onItemClick(Article article);
    }
}


