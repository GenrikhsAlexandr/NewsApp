package com.genrikhsalexandr.newsapp

import android.app.Application
import com.genrikhsalexandr.newsapp.di.DaggerApplicationComponent

class DetailApplication : Application() {

    val appComponent = DaggerApplicationComponent.create()


}