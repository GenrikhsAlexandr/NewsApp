package com.genrikhsalexandr.detailarticlefeature.di

import com.genrikhsaleksandr.core.di.CoreDataModule
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DetailModule::class,
        CoreDataModule::class,
    ]
)
interface DetailComponent {

    fun inject(detailFragment: DetailFragment)
}