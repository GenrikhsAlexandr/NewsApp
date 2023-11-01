package com.genrikhsaleksandr.savefeature.di

import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FavoritesModule::class,
        NavigationModule::class,
        CoreDataModule::class
    ]
)
interface FavoritesComponent {

    fun inject(favoritesFragment: FavoritesFragment)

}