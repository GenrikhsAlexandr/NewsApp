package com.genrikhsaleksandr.core.di.filterdi

import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.core.presentation.filter.FilterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FilterModule::class,
        NavigationModule::class,
        CoreDataModule::class
    ]
)
interface FilterComponent {

    fun inject(filterFragment: FilterFragment)

}