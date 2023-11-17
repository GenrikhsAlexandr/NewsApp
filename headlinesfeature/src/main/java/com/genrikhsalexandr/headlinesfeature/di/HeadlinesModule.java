package com.genrikhsalexandr.headlinesfeature.di;

import com.genrikhsaleksandr.core.domain.model.ArticleRepository;
import com.genrikhsaleksandr.core.navigation.Navigator;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.BusinessPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HeadlinesModule {

    @Provides
    @Singleton
    HeadlinesInteractor provideHeadlinesInteractor(ArticleRepository articleRepository) {
        return new HeadlinesInteractor(articleRepository);
    }

    @Provides
    @Singleton
    BusinessPresenter provideBusinessPresenter(
            HeadlinesInteractor headlinesInteractor,
            Navigator navigator
    ) {
        return new BusinessPresenter(headlinesInteractor, navigator);
    }
}