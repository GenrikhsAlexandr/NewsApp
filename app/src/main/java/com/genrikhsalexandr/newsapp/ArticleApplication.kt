package com.genrikhsalexandr.newsapp

import android.app.Application
import com.genrikhsaleksandr.core.di.DataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.savefeature.di.DaggerFavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponentProvider
import com.genrikhsalexandr.newsapp.data.repository.ArticleRepositoryImpl
import com.genrikhsalexandr.newsapp.di.DaggerApplicationComponent
import com.genrikhsalexandr.newsapp.navigation.NavigatorImpl

class ArticleApplication : Application(), FavoritesComponentProvider {

    private val navigationModule: NavigationModule by lazy {
        NavigationModule(
            navigator = NavigatorImpl()
        )
    }
/*
    private val dataModule:DataModule by lazy {
//    DataModule(
 //       repository = ArticleRepositoryImpl()
    )
    }
*/


    override fun provideFavoritesComponent(): FavoritesComponent {
        return DaggerFavoritesComponent.builder()
            .navigationModule(navigationModule)
            .build()    }

}