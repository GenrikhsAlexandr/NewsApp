package com.genrikhsalexandr.newsapp

import android.app.Application
import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.core.di.serachdi.DaggerSearchComponent
import com.genrikhsaleksandr.core.di.serachdi.SearchComponent
import com.genrikhsaleksandr.core.di.serachdi.SearchComponentProvider
import com.genrikhsaleksandr.savefeature.di.DaggerFavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponent
import com.genrikhsaleksandr.savefeature.di.FavoritesComponentProvider
import com.genrikhsalexandr.detailarticlefeature.di.DaggerDetailComponent
import com.genrikhsalexandr.detailarticlefeature.di.DetailComponent
import com.genrikhsalexandr.detailarticlefeature.di.DetailComponentProvider
import com.genrikhsalexandr.headlinesfeature.di.DaggerHeadlinesComponent
import com.genrikhsalexandr.headlinesfeature.di.HeadlinesComponent
import com.genrikhsalexandr.headlinesfeature.di.HeadlinesComponentProvider
import com.genrikhsalexandr.newsapp.di.ApplicationComponent
import com.genrikhsalexandr.newsapp.di.DaggerApplicationComponent
import com.genrikhsalexandr.newsapp.di.MainComponentProvider
import com.genrikhsalexandr.newsapp.navigation.NavigatorImpl

import com.genrikhsalexandr.souresfeature.di.DaggerSourcesComponent
import com.genrikhsalexandr.souresfeature.di.SourcesComponent
import com.genrikhsalexandr.souresfeature.di.SourcesComponentProvider

class ArticleApplication : Application(), FavoritesComponentProvider, MainComponentProvider,
    SourcesComponentProvider, DetailComponentProvider, SearchComponentProvider,
    HeadlinesComponentProvider {

    private val navigationModule: NavigationModule by lazy {
        NavigationModule(
            navigator = NavigatorImpl()
        )
    }
    private val applicationComponent: ApplicationComponent = DaggerApplicationComponent
        .factory().create(application = this, navigationModule = navigationModule)

    private val dataModule: CoreDataModule by lazy {
        CoreDataModule(
            repository = provideMainComponent().getArticleRepository(),
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

    override fun provideSourcesComponent(): SourcesComponent {
        return DaggerSourcesComponent.builder()
            .navigationModule(navigationModule)
            .coreDataModule(dataModule)
            .build()
    }

    override fun provideDetailComponent(): DetailComponent {
        return DaggerDetailComponent.builder()
            .coreDataModule(dataModule)
            .navigationModule(navigationModule)
            .build()
    }

    override fun provideSearchComponent(): SearchComponent {
        return DaggerSearchComponent.builder()
            .navigationModule(navigationModule)
            .coreDataModule(dataModule)
            .build()
    }

    override fun provideHeadlinesComponent(): HeadlinesComponent {
        return DaggerHeadlinesComponent.builder()
            .navigationModule(navigationModule)
            .coreDataModule(dataModule)
            .build()
    }
}