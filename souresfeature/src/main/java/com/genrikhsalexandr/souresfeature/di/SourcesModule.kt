package com.genrikhsalexandr.souresfeature.di

import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsalexandr.souresfeature.domain.SourcesInteractor
import com.genrikhsalexandr.souresfeature.presentation.sources.SourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Named
import javax.inject.Singleton

@Module
internal interface SourcesModule {

    @Binds
    @IntoMap
    @ViewModelKey(SourcesViewModel::class)
    fun sourceViewModel(viewModel: SourcesViewModel): ViewModel

    companion object {
        @Provides
        @Singleton
        @Named("SourcesInteractor")
        fun provideSourcesInteractor(sourcesRepository: ArticleRepository): SourcesInteractor {
            return SourcesInteractor(sourcesRepository)
        }
    }
}