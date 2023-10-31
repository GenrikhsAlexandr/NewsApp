package com.genrikhsaleksandr.savefeature.di

import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.savefeature.domain.FavoritesInteractor
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoritesModule {

    @Provides
    @Singleton
    fun provideFavoritesViewModel(interactor: FavoritesInteractor, navigator: Navigator): FavoritesViewModel {
        return FavoritesViewModel(interactor, navigator)
    }

    @Provides
    @Singleton
    fun provideFavoritesInteractor(articleRepository: ArticleRepository): FavoritesInteractor {
        return FavoritesInteractor(articleRepository)
    }
}
