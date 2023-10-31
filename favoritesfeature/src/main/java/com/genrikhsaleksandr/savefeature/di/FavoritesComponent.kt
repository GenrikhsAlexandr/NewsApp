package com.genrikhsaleksandr.savefeature.di

import android.app.Application
import com.genrikhsaleksandr.core.di.DataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FavoritesModule::class,
        NavigationModule::class,
        DataModule::class
    ]
)
interface FavoritesComponent {

    fun inject(favoritesFragment: FavoritesFragment)

}
