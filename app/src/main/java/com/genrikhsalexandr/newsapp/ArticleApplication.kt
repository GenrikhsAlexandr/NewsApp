package com.genrikhsalexandr.newsapp

import android.app.Application
import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.savefeature.di.DaggerFavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponentProvider
import com.genrikhsalexandr.newsapp.di.ApplicationComponent
import com.genrikhsalexandr.newsapp.di.DaggerApplicationComponent
import com.genrikhsalexandr.newsapp.di.MainComponentProvider
import com.genrikhsalexandr.newsapp.navigation.NavigatorImpl

class ArticleApplication : Application(), FavoritesComponentProvider, MainComponentProvider {

    private val navigationModule: NavigationModule by lazy {
        NavigationModule(
            navigator = NavigatorImpl()
        )
    }
    private val applicationComponent: ApplicationComponent = DaggerApplicationComponent
        .factory().create(application = this, navigationModule = navigationModule)

    private val dataModule: CoreDataModule by lazy {
        CoreDataModule(
            repository = provideMainComponent().getArticleRepository()
        )
    }

    override fun provideFavoritesComponent(): FavoritesComponent {
        return DaggerFavoritesComponent.builder()
            .navigationModule(navigationModule)
            .coreDataModule(dataModule)
            .build()
    }

    override fun provideMainComponent(): ApplicationComponent {
        return applicationComponent
    }
}