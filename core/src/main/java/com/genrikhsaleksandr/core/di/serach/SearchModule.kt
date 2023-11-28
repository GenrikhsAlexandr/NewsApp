package com.genrikhsaleksandr.core.di.serach

import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.presentation.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface SearchModule {

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun searchViewModel(viewModel: SearchViewModel): ViewModel
}