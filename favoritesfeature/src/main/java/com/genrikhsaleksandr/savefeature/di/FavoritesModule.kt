package com.genrikhsaleksandr.savefeature.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.savefeature.domain.FavoritesInteractor
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal interface FavoritesModule {

    @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    fun favoritesViewModel(viewModel: FavoritesViewModel): ViewModel

    companion object {
        @Provides
        @Singleton
        fun provideFavoritesInteractor(articleRepository: ArticleRepository): FavoritesInteractor {
            return FavoritesInteractor(articleRepository)
        }
    }
}