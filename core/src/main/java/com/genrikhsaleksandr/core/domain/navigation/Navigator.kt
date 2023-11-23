package com.genrikhsaleksandr.core.navigation

import androidx.fragment.app.FragmentManager
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsaleksandr.core.domain.navigation.Screen

interface Navigator {

    fun interface Listener {
        fun onNavigated(destination: Screen)
    }

    var listener: Listener?

    fun navigateToDetailsArticle(article: Article, fragmentManager: FragmentManager)

    fun navigateToFavorites(fragmentManager: FragmentManager)

    fun navigateToHeadlines(fragmentManager: FragmentManager)

    fun navigateToSources(fragmentManager: FragmentManager)

    fun navigateToArticlesSource(articlesSource: Source, fragmentManager: FragmentManager)

    fun navigateToSearch(fragmentManager: FragmentManager)

    fun navigateToFilter(fragmentManager: FragmentManager)

    fun navigateArticlesSourceToSources(fragmentManager: FragmentManager)

    fun navigateBackSearch(fragmentManager: FragmentManager)
    fun navigateBackFilter(fragmentManager: FragmentManager)
    fun navigateSavedFilter(fragmentManager: FragmentManager)
    fun navigateBackDetailArticle(fragmentManager: FragmentManager)
    fun navigateBackDetailArticleForSearch(fragmentManager: FragmentManager)

    fun navigateToDetailsArticleForSearch(article: Article, fragmentManager: FragmentManager)


}