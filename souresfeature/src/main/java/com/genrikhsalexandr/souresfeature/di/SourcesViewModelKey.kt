package com.genrikhsalexandr.souresfeature.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)