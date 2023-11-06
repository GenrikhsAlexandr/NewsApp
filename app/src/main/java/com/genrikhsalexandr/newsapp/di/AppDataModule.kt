package com.genrikhsalexandr.newsapp.di

import android.app.Application
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.savefeature.data.NewsDtoMapper
import com.genrikhsaleksandr.savefeature.data.database.AppDatabase
import com.genrikhsaleksandr.savefeature.data.database.ArticleDao
import com.genrikhsalexandr.newsapp.data.repository.ArticleRepositoryImpl
import com.genrikhsalexandr.souresfeature.data.SourcesDtoMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppDataModule {

    @Provides
    @Singleton
    fun provideArticleRepository(
        dao: ArticleDao,
        articleMapper: NewsDtoMapper,
        sourcesMapper: SourcesDtoMapper
    ): ArticleRepository {
        return ArticleRepositoryImpl(
            dao,
            articleMapper,
            sourcesMapper
        )
    }

    @Provides
    @Singleton
    fun provideMapperNewsDto(
    ): NewsDtoMapper {
        return NewsDtoMapper()
    }

    @Provides
    @Singleton
    fun provideMapperSourcesDto(
    ): SourcesDtoMapper {
        return SourcesDtoMapper()
    }

    @Provides
    fun provideArticleDao(
        application: Application
    ): ArticleDao {
        return AppDatabase.getInstance(application).articleRequestDao()
    }
}