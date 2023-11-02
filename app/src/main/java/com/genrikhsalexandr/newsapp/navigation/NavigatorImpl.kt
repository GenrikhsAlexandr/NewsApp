package com.genrikhsalexandr.newsapp.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesFragment
import com.genrikhsalexandr.newsapp.R

class NavigatorImpl : Navigator {

    override fun navigateToArticleDetails(article: Article, fragmentManager: FragmentManager) {
        val detailFragment = com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, detailFragment)
            addToBackStack(null)
        }
    }

    override fun navigateToFavorites(fragmentManager: FragmentManager) {
            val favoritesFragment = FavoritesFragment.newInstance()
            fragmentManager.commit {
                replace(R.id.fragment_container, favoritesFragment)
                addToBackStack(null)
            }    }
}
