package com.genrikhsalexandr.searchfeature.di

import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsalexandr.searchfeature.presentation.SearchFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        SearchModule::class,
        NavigationModule::class,
        CoreDataModule::class,
    ]
)
interface SearchComponent {

    fun inject(favoritesFragment: SearchFragment)
}