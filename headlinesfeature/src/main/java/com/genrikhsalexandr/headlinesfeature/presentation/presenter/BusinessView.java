package com.genrikhsalexandr.headlinesfeature.presentation.presenter;

import androidx.annotation.UiThread;

import com.genrikhsalexandr.headlinesfeature.presentation.ArticleItemList;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface BusinessView extends MvpView {


    @StateStrategyType(AddToEndSingleStrategy.class)
    void setLoading(Boolean isLoading);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showNews(List<ArticleItemList> news);
}