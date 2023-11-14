package com.genrikhsalexandr.headlinesfeature.presentation.presenter;

import androidx.fragment.app.FragmentManager;

import com.genrikhsaleksandr.core.domain.Category;
import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsaleksandr.core.navigation.Navigator;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;
import com.genrikhsalexandr.headlinesfeature.presentation.ArticleItemList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class HeadlinesPresenterImpl{ /*extends MvpPresenter<HeadlinesView> implements HeadlinesPresenter {

    private final HeadlinesInteractor interactor;
    private final Navigator navigator;

    @Inject
    public HeadlinesPresenterImpl(HeadlinesInteractor interactor, Navigator navigator) {
        this.interactor = interactor;
        this.navigator = navigator;
    }

*//*    @Override
    public void init() {
        getViewState().showNews(); // Показать загрузку (если необходимо)
        App.getInstance().getAppComponent().getExecutorService().execute(() -> {
            try {
                String articles = interactor.getArticlesList(Category.GENERAL.toString());
                List<ArticleItemList> news = convertToNewsItemList(articles);
                getViewState().showNews(news);
            } catch (Exception e) {
                e.printStackTrace();
                getViewState().showError(e.getMessage());
            }
        });
    }*//*

    @Override
    public void onNewsItemClick(Article article, FragmentManager fragmentManager) {
        navigator.navigateToDetailsArticle(article, fragmentManager);
    }

    private List<ArticleItemList> convertToNewsItemList(String articles) {
        List<ArticleItemList> newsItemList = new ArrayList<>();
        for (Article article : articles) {
            ArticleItemList newsItem = new ArticleItemList(
                    article.getSourceName(),
                    article.getTitle(),
                    article.getUrlToImage(),
                    article,
                    article.getId()
            );
            newsItemList.add(newsItem);
        }
        return newsItemList;
    }*/
}