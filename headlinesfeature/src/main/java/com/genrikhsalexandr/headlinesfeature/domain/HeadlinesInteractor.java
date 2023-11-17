package com.genrikhsalexandr.headlinesfeature.domain;

import com.genrikhsaleksandr.core.domain.Category;
import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsaleksandr.core.domain.model.ArticleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class HeadlinesInteractor {
    private final ArticleRepository repository;

    @Inject
    public HeadlinesInteractor(ArticleRepository repository) {
        this.repository = repository;
    }

    public Single<List<Article>> getArticlesList(Category category) {
        return Single.create(emitter -> {
            List<Article> result = repository.getArticlesForCategoryBlocking(category);
            if (result == null) {
                emitter.onError(new Exception("No articles for category " + category));
            }
            emitter.onSuccess(result);
        });
    }
}