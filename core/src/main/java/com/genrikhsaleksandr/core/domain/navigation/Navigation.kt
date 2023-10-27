package com.genrikhsaleksandr.core.domain.navigation

interface Navigation {
    fun navigateToFavoriteFragment()

    fun navigateToSourcesFragment()

    fun navigateToHeadlinesFragment()

    fun navigateToSearchFragment()

    fun navigateToFilterFragment()

    fun navigateToArticleFragment()
}