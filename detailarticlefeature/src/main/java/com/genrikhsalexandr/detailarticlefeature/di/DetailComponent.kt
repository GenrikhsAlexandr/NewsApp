package com.genrikhsalexandr.detailarticlefeature.di

import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsaleksandr.core.di.NavigationModule
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailForSearchFragment
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DetailModule::class,
        CoreDataModule::class,
        NavigationModule::class
    ]
)
interface DetailComponent {

    fun inject1(detailFragment: DetailFragment)
    fun inject2(detailForSearchFragment: DetailForSearchFragment)
}