package com.genrikhsalexandr.headlinesfeature.presentation.presenter;


import androidx.fragment.app.FragmentManager;

import com.genrikhsaleksandr.core.domain.Category;
import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsaleksandr.core.domain.model.SearchRepository;
import com.genrikhsaleksandr.core.navigation.Navigator;
import com.genrikhsaleksandr.core.presentation.ArticleItemList;
import com.genrikhsaleksandr.core.presentation.ItemList;
import com.genrikhsaleksandr.core.presentation.LoadingItemList;
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
public class TechnologyPresenter extends MvpPresenter<HeadlinesView> {
    Navigator navigator;
    HeadlinesInteractor interactor;
    Disposable disposable;
    SearchRepository searchRepository;
    private List<Article> articles;
    private Integer currentPage = 1;
    private Boolean isLoading;

    @Inject
    public TechnologyPresenter(
            HeadlinesInteractor interactor,
            Navigator navigator,
            SearchRepository searchRepository

    ) {
        this.interactor = interactor;
        this.navigator = navigator;
        this.searchRepository = searchRepository;
        getViewState().setLoading(true);
        loadFirstPage();
    }

    private void loadFirstPage() {
        currentPage = 1;
        getViewState().setLoading(true);
        isLoading = true;
        disposable = interactor.getArticlesList(currentPage, Category.TECHNOLOGY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        articles -> {
                            this.articles = articles;
                            onPageLoaded();
                        },
                        throwable -> {
                            onError(throwable);
                            isLoading = false;
                        });
    }

    public void loadNextPage() {
        if (!isLoading) {
            currentPage++;
            getViewState().setLoading(true);
            isLoading = true;
            disposable = interactor.getArticlesList(currentPage, Category.TECHNOLOGY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            articles -> {
                                this.articles.addAll(articles);
                                onPageLoaded();
                            },
                            throwable -> {
                                onError(throwable);
                                isLoading = false;
                            });
        }
    }

    private void onError(Throwable throwable) {
        String errorMessage = "An error occurred: " + throwable.getMessage();
        System.err.println(errorMessage);
    }

    private void onPageLoaded() {
        List<ItemList> articlesItems = new ArrayList<>(articles.size());
        articles.forEach(article ->
                articlesItems.add(
                        new ArticleItemList(
                                article.getSourceName(),
                                article.getTitle(),
                                article.getUrlToImage(),
                                article,
                                article.getId()
                        )
                ));
        articlesItems.add(LoadingItemList.INSTANCE);
        getViewState().setLoading(false);
        isLoading = false;
        searchRepository.setArticles(articles);
        getViewState().showArticles(articlesItems);
    }

    public void onArticleItemClick(Article article, FragmentManager fragmentManager) {
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
        loadFirstPage();
    }
}