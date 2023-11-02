package com.genrikhsalexandr.newsapp.di

import android.app.Application
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsalexandr.newsapp.presentation.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppDataModule::class])
@Singleton
interface ApplicationComponent {

   //fun inject(mainFragment: MainFragment)

    fun getArticleRepository(): ArticleRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}