package com.genrikhsalexandr.newsapp

import android.app.Application
import com.genrikhsalexandr.newsapp.di.DaggerApplicationComponent

class ArticleApplication : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}