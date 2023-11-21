package com.genrikhsalexandr.headlinesfeature.di;

import com.genrikhsaleksandr.core.domain.model.ArticleRepository;
import com.genrikhsaleksandr.core.domain.model.SearchRepository;
import com.genrikhsaleksandr.core.navigation.Navigator;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.BusinessPresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.EntertainmentPresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.GeneralPresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.HealthPresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.SciencePresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.SportPresenter;
import com.genrikhsalexandr.headlinesfeature.presentation.presenter.TechnologyPresenter;

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
            Navigator navigator,
            SearchRepository repository
    ) {
        return new BusinessPresenter(headlinesInteractor, navigator, repository);
    }

    @Provides
    @Singleton
    EntertainmentPresenter provideEntertainmentPresenter(
            HeadlinesInteractor headlinesInteractor,
            Navigator navigator,
            SearchRepository repository
    ) {
        return new EntertainmentPresenter(headlinesInteractor, navigator, repository);
    }

    @Provides
    @Singleton
    GeneralPresenter provideGeneralPresenter(
            HeadlinesInteractor headlinesInteractor,
            Navigator navigator,
            SearchRepository repository
    ) {
        return new GeneralPresenter(headlinesInteractor, navigator, repository);
    }

    @Provides
    @Singleton
    HealthPresenter provideHealthPresenter(
            HeadlinesInteractor headlinesInteractor,
            Navigator navigator,
            SearchRepository repository

    ) {
        return new HealthPresenter(headlinesInteractor, navigator, repository);
    }

    @Provides
    @Singleton
    SciencePresenter provideSciencePresenter(
            HeadlinesInteractor headlinesInteractor,
            Navigator navigator,
            SearchRepository repository

    ) {
        return new SciencePresenter(headlinesInteractor, navigator, repository);
    }

    @Provides
    @Singleton
    SportPresenter provideSportPresenter(
            HeadlinesInteractor headlinesInteractor,
            Navigator navigator,
            SearchRepository repository

    ) {
        return new SportPresenter(headlinesInteractor, navigator, repository);
    }

    @Provides
    @Singleton
    TechnologyPresenter provideTechnologyPresenter(
            HeadlinesInteractor headlinesInteractor,
            Navigator navigator,
            SearchRepository repository

    ) {
        return new TechnologyPresenter(headlinesInteractor, navigator, repository);
    }
}