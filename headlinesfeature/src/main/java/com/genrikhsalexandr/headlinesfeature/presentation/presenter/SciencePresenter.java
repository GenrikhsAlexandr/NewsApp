package com.genrikhsalexandr.headlinesfeature.presentation.presenter;


import androidx.fragment.app.FragmentManager;

import com.genrikhsaleksandr.core.domain.Category;
import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsaleksandr.core.navigation.Navigator;
import com.genrikhsaleksandr.core.presentation.ArticleItemList;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SciencePresenter extends MvpPresenter<HeadlinesView> {

    Navigator navigator;

    HeadlinesInteractor interactor;


    @Inject
    public SciencePresenter(
            HeadlinesInteractor interactor,
            Navigator navigator
    ) {
        this.interactor = interactor;
        this.navigator = navigator;
        getViewState().setLoading(true);
        interactor.getArticlesList(Category.SCIENCE)
                .subscribe(this::onArticlesLoaded, this::onError);
    }

    private void onError(Throwable throwable) {
        String errorMessage = "An error occurred: " + throwable.getMessage();
        System.err.println(errorMessage);
    }

    private void onArticlesLoaded(List<Article> articles) {
        List<ArticleItemList> articlesItems = new ArrayList<>(articles.size());
        articles.forEach(article -> articlesItems.add(
                new ArticleItemList(
                        article.getSourceName(),
                        article.getTitle(),
                        article.getUrlToImage(),
                        article,
                        article.getId()
                )
        ));
        System.out.println("articles = " + articles.size());
        getViewState().setLoading(false);
        getViewState().showArticles(articlesItems);
    }

    public void onNewsItemClick(Article article, FragmentManager fragmentManager) {
        navigator.navigateToDetailsArticle(article, fragmentManager);
    }
}