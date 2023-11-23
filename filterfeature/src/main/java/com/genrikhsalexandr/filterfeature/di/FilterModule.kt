package com.genrikhsalexandr.filterfeature.di

import androidx.lifecycle.ViewModel
import com.genrikhsalexandr.filterfeature.presentation.FilterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface FilterModule {

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    fun sourceViewModel(viewModel: FilterViewModel): ViewModel
}