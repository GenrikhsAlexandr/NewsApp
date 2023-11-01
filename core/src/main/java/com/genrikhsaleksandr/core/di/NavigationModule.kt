package com.genrikhsaleksandr.core.di

import com.genrikhsaleksandr.core.navigation.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NavigationModule(
    private val navigator: Navigator
) {

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return navigator
    }
}