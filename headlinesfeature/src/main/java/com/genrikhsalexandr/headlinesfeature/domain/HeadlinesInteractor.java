package com.genrikhsalexandr.headlinesfeature.domain;

import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsaleksandr.core.domain.model.ArticleRepository;

import java.util.List;

import javax.inject.Inject;


public class HeadlinesInteractor {
    private ArticleRepository repository;

    @Inject
    public HeadlinesInteractor(ArticleRepository repository) {
        this.repository = repository;
    }

 /*   public List<Article> getArticlesList(String category) throws Exception {
        return repository.getArticles()
    }

    public String getArticlesList(String category) throws Exception {
        return category;
    }*/
}
