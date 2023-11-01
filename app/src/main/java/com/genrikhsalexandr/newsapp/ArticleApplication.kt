package com.genrikhsalexandr.newsapp

import android.app.Application
import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.savefeature.di.DaggerFavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponentProvider
import com.genrikhsalexandr.newsapp.di.ApplicationComponent
import com.genrikhsalexandr.newsapp.di.DaggerApplicationComponent
import com.genrikhsalexandr.newsapp.navigation.NavigatorImpl

class ArticleApplication : Application(), FavoritesComponentProvider {


    private val navigationModule: NavigationModule by lazy {
        NavigationModule(
            navigator = NavigatorImpl()
        )
    }
    private val applicationComponent: ApplicationComponent = DaggerApplicationComponent
        .factory().create(application = this)

    private val dataModule: CoreDataModule by lazy {
        CoreDataModule(
            repository = applicationComponent.getArticleRepository()
        )
    }

    override fun provideFavoritesComponent(): FavoritesComponent {
        return DaggerFavoritesComponent.builder()
            .navigationModule(navigationModule)
            .coreDataModule(dataModule)
            .build()
    }
}