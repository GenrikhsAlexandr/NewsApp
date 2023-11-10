package com.genrikhsalexandr.headlinesfeature.presentation;

import com.genrikhsaleksandr.core.domain.model.Article;

public class ArticleItemList {
    private final String sourceName;
    private final String title;
    private final String urlToImage;
    private final Article article;
    private final long id;

        public ArticleItemList(String sourceName, String title, String urlToImage, Article article, long id) {
            this.sourceName = sourceName;
            this.title = title;
            this.urlToImage = urlToImage;
            this.article = article;
            this.id = id;
        }

        public String getSourceName() {
            return sourceName;
        }

        public String getTitle() {
            return title;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public Article getArticle() {
            return article;
        }

        public long getId() {
            return id;
        }
}