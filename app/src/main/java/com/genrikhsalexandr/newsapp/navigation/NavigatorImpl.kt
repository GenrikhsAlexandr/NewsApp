package com.genrikhsalexandr.newsapp.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.navigation.Screen
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment
import com.genrikhsalexandr.newsapp.R

class NavigatorImpl : Navigator {


    override var listener: Navigator.Listener? = null
    override fun navigateToDetailsArticle(article: Article, fragmentManager: FragmentManager) {
        val detailFragment = DetailFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, detailFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.DETAIL_ARTICLE)
    }
}