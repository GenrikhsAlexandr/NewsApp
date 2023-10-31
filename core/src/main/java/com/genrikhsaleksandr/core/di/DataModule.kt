package com.genrikhsaleksandr.core.di

import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import dagger.Provides
import javax.inject.Singleton

class DataModule(
private val repository: ArticleRepository
) {
    @Provides
    @Singleton
    fun provideArticleRepository(): ArticleRepository {
        return repository
    }
}