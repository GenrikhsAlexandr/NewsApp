package com.genrikhsalexandr.newsapp.di

import android.app.Application
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.core.domain.model.ArticleRepository
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.newsapp.navigation.NavigatorImpl
import com.genrikhsalexandr.newsapp.presentation.MainFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AppDataModule::class,
        NavigationModule::class,
        MainFragmentViewModelModule::class]
)
@Singleton
interface ApplicationComponent {

    fun inject(mainFragment: MainFragment)

    fun getArticleRepository(): ArticleRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            navigationModule: NavigationModule
        ): ApplicationComponent
    }
}