package com.genrikhsaleksandr.core.navigation

import androidx.fragment.app.FragmentManager
import com.genrikhsaleksandr.core.domain.navigation.Screen
import com.genrikhsaleksandr.core.domain.model.Article

interface Navigator {

    fun interface Listener {
        fun onNavigated(destination: Screen)
    }

    var listener: Listener?

    fun navigateToDetailsArticle(article: Article, fragmentManager: FragmentManager)

  /*  fun navigateToFavorites(fragmentManager: FragmentManager)

    fun navigateToHeadlines(fragmentManager: FragmentManager)

    fun navigateToSources(fragmentManager: FragmentManager)
*/
}