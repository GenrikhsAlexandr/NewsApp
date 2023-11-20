package com.genrikhsaleksandr.core.di.serachdi

import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.core.presentation.search.SearchFragment
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

    fun inject(searchFragment: SearchFragment)
}