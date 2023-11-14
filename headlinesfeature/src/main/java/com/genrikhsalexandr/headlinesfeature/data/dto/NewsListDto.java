package com.genrikhsalexandr.headlinesfeature.data.dto;

import java.util.List;

public class NewsListDto {

    private List<ArticleDto> articles;
    private String status;
    private int totalResults;

    public NewsListDto(List<ArticleDto> articles, String status, int totalResults) {
        this.articles = articles;
        this.status = status;
        this.totalResults = totalResults;
    }

    public List<ArticleDto> getArticles() {
        return articles;
    }
}