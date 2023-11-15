package com.genrikhsalexandr.searchfeature.di

import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsalexandr.searchfeature.domain.SearchInteractor
import com.genrikhsalexandr.searchfeature.presentation.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal interface SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun searchViewModel(viewModel: SearchViewModel): ViewModel

    companion object {
        @Provides
        @Singleton
        fun provideSearchInteractor(articleRepository: ArticleRepository): SearchInteractor {
            return SearchInteractor(articleRepository)
        }
    }
}