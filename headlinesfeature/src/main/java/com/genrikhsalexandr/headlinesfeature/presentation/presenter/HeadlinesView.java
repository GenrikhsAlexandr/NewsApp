package com.genrikhsalexandr.headlinesfeature.presentation.presenter;

import com.genrikhsaleksandr.core.presentation.ArticleItemList;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface HeadlinesView extends MvpView {

    @StateStrategyType(AddToEndSingleStrategy.class)
    void setLoading(Boolean isLoading);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showArticles(List<ArticleItemList> news);
}