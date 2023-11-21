package com.genrikhsalexandr.headlinesfeature.presentation.presenter;


import androidx.fragment.app.FragmentManager;

import com.genrikhsaleksandr.core.domain.Category;
import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsaleksandr.core.domain.model.SearchRepository;
import com.genrikhsaleksandr.core.navigation.Navigator;
import com.genrikhsaleksandr.core.presentation.ArticleItemList;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class GeneralPresenter extends MvpPresenter<HeadlinesView> {

    Navigator navigator;
    HeadlinesInteractor interactor;
    Disposable disposable;
    SearchRepository repository;


    @Inject
    public GeneralPresenter(
            HeadlinesInteractor interactor,
            Navigator navigator,
            SearchRepository repository

    ) {
        this.interactor = interactor;
        this.navigator = navigator;
        this.repository = repository;
        getViewState().setLoading(true);
        disposable = interactor.getArticlesList(Category.GENERAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
        repository.setArticle(articles);
        getViewState().showArticles(articlesItems);
    }

    public void onNewsItemClick(Article article, FragmentManager fragmentManager) {
        navigator.navigateToDetailsArticle(article, fragmentManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void onRefresh() {
        getViewState().setLoading(true);
        disposable = interactor.getArticlesList(Category.GENERAL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onArticlesLoaded, this::onError);
    }
}
