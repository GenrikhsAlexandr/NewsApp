package com.genrikhsaleksandr.core.navigation

import androidx.fragment.app.FragmentManager
import com.genrikhsaleksandr.core.domain.navigation.Screen
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.Source

interface Navigator {

    fun interface Listener {
        fun onNavigated(destination: Screen)
    }

    var listener: Listener?

    fun navigateToDetailsArticle(article: Article, fragmentManager: FragmentManager)

    fun navigateToFavorites(fragmentManager: FragmentManager)

    fun navigateToHeadlines(fragmentManager: FragmentManager)

    fun navigateToSources(fragmentManager: FragmentManager)

    fun navigateToArticlesSource(articlesSource:Source, fragmentManager: FragmentManager)

    fun navigateToSearch(fragmentManager: FragmentManager)

    fun navigateToFilter(fragmentManager: FragmentManager)

   fun navigateToFilterDate(fragmentManager: FragmentManager)

}