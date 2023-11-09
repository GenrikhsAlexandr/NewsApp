package com.genrikhsalexandr.souresfeature.di

import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsalexandr.souresfeature.presentation.articlessource.ArticlesSourceFragment
import com.genrikhsalexandr.souresfeature.presentation.sources.SourcesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SourcesModule::class,
        NavigationModule::class,
        CoreDataModule::class,
    ArticlesSourceModule::class
    ]
)
interface SourcesComponent {

    fun inject(sourcesFragment: SourcesFragment)

    fun inject(articlesSourceFragment: ArticlesSourceFragment)
}