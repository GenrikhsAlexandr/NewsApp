package com.genrikhsalexandr.detailarticlefeature.di

import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsalexandr.detailarticlefeature.domain.DetailInteractor
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal interface DetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun detailViewModel(viewModel: DetailViewModel): ViewModel

    companion object {
        @Provides
        @Singleton
        fun provideDetailInteractor(articleRepository: ArticleRepository): DetailInteractor {
            return DetailInteractor(articleRepository)
        }
    }
}