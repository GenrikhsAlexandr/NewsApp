package com.genrikhsalexandr.souresfeature.di

import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsalexandr.souresfeature.domain.SourcesInteractor
import com.genrikhsalexandr.souresfeature.presentation.articlessource.ArticlesSourceViewModel
import com.genrikhsalexandr.souresfeature.presentation.sources.SourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named
import javax.inject.Singleton

@Module
interface ArticlesSourceModule {
    @Binds
    @IntoMap
    @ViewModelKey(ArticlesSourceViewModel::class)
    fun articlesSourceViewModel(viewModel: ArticlesSourceViewModel): ViewModel

    companion object {
        @Provides
        @Singleton
        @Named("ArticlesSourcesInteractor")
        fun provideArticlesSourcesInteractor(articlesSourcesRepository: ArticleRepository): SourcesInteractor {
            return SourcesInteractor(articlesSourcesRepository)
        }
    }
}