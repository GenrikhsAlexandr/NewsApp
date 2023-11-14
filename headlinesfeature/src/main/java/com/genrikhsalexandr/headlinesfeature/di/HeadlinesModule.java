package com.genrikhsalexandr.headlinesfeature.di;

import androidx.lifecycle.ViewModel;

import com.genrikhsaleksandr.core.domain.model.ArticleRepository;
import com.genrikhsalexandr.headlinesfeature.domain.HeadlinesInteractor;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public interface  HeadlinesModule{

    @Module
    static class HeadlinesModuleImpl {
        @Provides
        @Singleton
        HeadlinesInteractor provideFavoritesInteractor(ArticleRepository articleRepository) {
            return new HeadlinesInteractor(articleRepository);
        }
    }
}



