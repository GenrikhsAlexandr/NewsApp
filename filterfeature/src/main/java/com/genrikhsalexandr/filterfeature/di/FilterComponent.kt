package com.genrikhsalexandr.filterfeature.di

import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsalexandr.filterfeature.presentation.FilterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        FilterModule::class,
        NavigationModule::class,
    ]
)
interface FilterComponent {

    fun inject(filterFragment: FilterFragment)

}