package com.genrikhsalexandr.newsapp.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment

class NavigatorImpl : Navigator {

    override fun navigateToArticleDetails(article: Article, fragmentManager: FragmentManager) {
        val detailFragment = com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, detailFragment)
            addToBackStack(null)
        }
    }
}
