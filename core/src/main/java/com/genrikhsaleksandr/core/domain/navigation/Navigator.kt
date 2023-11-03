package com.genrikhsaleksandr.core.navigation

import androidx.fragment.app.FragmentManager
import com.genrikhsaleksandr.core.domain.model.Article

interface Navigator {

    fun navigateToArticleDetails(article: Article, fragmentManager: FragmentManager)

    fun navigateToFavorites(fragmentManager: FragmentManager)

    fun navigateToHeadlines(fragmentManager: FragmentManager)

    fun navigateToSources(fragmentManager: FragmentManager)


}