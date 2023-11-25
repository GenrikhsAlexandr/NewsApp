package com.genrikhsaleksandr.core.di.filter

import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.presentation.filter.FilterReducer
import com.genrikhsaleksandr.core.presentation.filter.FilterViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
internal interface FilterModule {

    @Binds
    @IntoMap
    @ViewModelKey(FilterViewModel::class)
    fun sourceViewModel(viewModel: FilterViewModel): ViewModel

    @Module
    companion object {

        @Provides
        @Singleton
        fun provideFilterReducer(): FilterReducer = FilterReducer()
    }
}