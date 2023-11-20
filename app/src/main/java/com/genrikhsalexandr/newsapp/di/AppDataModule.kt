package com.genrikhsalexandr.newsapp.di

import android.app.Application
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.core.data.NewsListDtoMapper
import com.genrikhsaleksandr.core.data.database.AppDatabase
import com.genrikhsaleksandr.core.data.database.ArticleDao
import com.genrikhsalexandr.newsapp.data.repository.ArticleRepositoryImpl
import com.genrikhsalexandr.souresfeature.data.ArticlesSourceDtoMapper
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
        mapper: NewsListDtoMapper,
        sourcesMapper: SourcesDtoMapper,
        articlesSourceMapper: ArticlesSourceDtoMapper,
    ): ArticleRepository {
        return ArticleRepositoryImpl(
            dao,
            mapper,
            sourcesMapper,
            articlesSourceMapper
        )
    }

    @Provides
    @Singleton
    fun provideMapperNewsDto(
    ): NewsListDtoMapper {
        return NewsListDtoMapper()
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