package com.genrikhsalexandr.headlinesfeature.presentation.presenter;

import androidx.annotation.UiThread;

import com.genrikhsalexandr.headlinesfeature.presentation.ArticleItemList;

import java.util.List;

import moxy.MvpView;

public interface HeadlinesView extends MvpView {

@UiThread
    void showNews(List<ArticleItemList> news);
}
