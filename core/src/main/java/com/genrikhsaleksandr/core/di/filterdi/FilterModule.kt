package com.genrikhsaleksandr.core.di.filterdi

import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.presentation.filter.FilterViewModel
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