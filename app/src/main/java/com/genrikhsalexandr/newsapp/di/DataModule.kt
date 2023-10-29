package com.genrikhsalexandr.newsapp.di

import android.app.Application
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.savefeature.data.database.AppDatabase
import com.genrikhsaleksandr.savefeature.data.database.ArticleDao
import com.genrikhsalexandr.newsapp.data.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

       @Binds
       fun bindArticleRepository(impl: ArticleRepositoryImpl): ArticleRepository

       companion object {
           @Provides
           fun provideArticleDao(
               application: Application
           ): ArticleDao {
               return AppDatabase.getInstance(application).articleRequestDao()
           }

       }
}