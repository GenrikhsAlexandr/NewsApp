package com.genrikhsaleksandr.core.di

import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreDataModule(
    private val repository: ArticleRepository
) {
    @Provides
    @Singleton
    fun provideArticleRepository(): ArticleRepository {
        return repository
    }
}