package com.genrikhsalexandr.newsapp.di

import android.app.Application
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.savefeature.data.NewsDtoMapper
import com.genrikhsaleksandr.savefeature.data.database.AppDatabase
import com.genrikhsaleksandr.savefeature.data.database.ArticleDao
import com.genrikhsaleksandr.savefeature.domain.FavoritesInteractor
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesViewModel
import com.genrikhsalexandr.newsapp.data.repository.ArticleRepositoryImpl
import com.genrikhsalexandr.newsapp.presentation.MainFragmentViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppDataModule {

    @Provides
    @Singleton
    fun provideArticleRepository(
        dao: ArticleDao,
        mapper: NewsDtoMapper
    ): ArticleRepository {
        return ArticleRepositoryImpl(
            dao,
            mapper
        )
    }

    @Provides
    @Singleton
    fun provideMapperNewsDto(
    ): NewsDtoMapper {
        return NewsDtoMapper()
    }

    @Provides
    fun provideArticleDao(
        application: Application
    ): ArticleDao {
        return AppDatabase.getInstance(application).articleRequestDao()
    }
}