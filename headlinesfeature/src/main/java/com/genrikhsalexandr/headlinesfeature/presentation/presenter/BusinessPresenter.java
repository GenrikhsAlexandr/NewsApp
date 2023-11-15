package com.genrikhsalexandr.headlinesfeature.presentation.presenter;


import androidx.fragment.app.FragmentManager;

import com.genrikhsaleksandr.core.domain.model.Article;
import com.genrikhsaleksandr.core.navigation.Navigator;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;
import com.genrikhsalexandr.headlinesfeature.presentation.ArticleItemList;

import java.util.List;

import javax.inject.Inject;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class BusinessPresenter extends MvpPresenter<BusinessView> {

    @Inject
    Navigator navigator;

    HeadlinesInteractor interactor;

    public BusinessPresenter() {
        getViewState().setLoading(true);
        // Здесь надо будет делать запрос получить список новостей
    }

    public void onNewsItemClick(Article article, FragmentManager fragmentManager) {
        navigator.navigateToDetailsArticle(article, fragmentManager);
    }

}