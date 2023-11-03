package com.genrikhsalexandr.newsapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.genrikhsalexandr.newsapp.presentation.MainFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainFragmentViewModelModule {

    @Binds
    fun bindMainViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @MainViewModelKey(MainFragmentViewModel::class)
    fun mainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel

}