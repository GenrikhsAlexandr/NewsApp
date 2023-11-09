package com.genrikhsalexandr.newsapp.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsaleksandr.core.domain.navigation.Screen
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesFragment
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment
import com.genrikhsalexandr.filterfeature.presentation.FilterFragment
import com.genrikhsalexandr.headlinesfeature.presentation.HeadlinesFragment
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.searchfeature.presentation.SearchFragment
import com.genrikhsalexandr.souresfeature.presentation.articlessource.ArticlesSourceFragment
import com.genrikhsalexandr.souresfeature.presentation.sources.SourcesFragment

class NavigatorImpl : Navigator {


    override var listener: Navigator.Listener? = null
    override fun navigateToDetailsArticle(article: Article, fragmentManager: FragmentManager) {
        val detailFragment = DetailFragment.newInstance(article)
        fragmentManager.commit {
            replace(R.id.fragment_container, detailFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.DetailArticle)
    }

    override fun navigateToFavorites(fragmentManager: FragmentManager) {
        val favoritesFragment = FavoritesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, favoritesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Favorites)
    }

    override fun navigateToHeadlines(fragmentManager: FragmentManager) {
        val headlinesFragment = HeadlinesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, headlinesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Headlines)
    }

    override fun navigateToSources(fragmentManager: FragmentManager) {
        val sourcesFragment = SourcesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, sourcesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Sources)
    }

    override fun navigateToArticlesSource(
        articlesSource: Source,
        fragmentManager: FragmentManager
    ) {
        val sourceNesFragment = ArticlesSourceFragment.newInstance(articlesSource)
        fragmentManager.commit {
            replace(R.id.fragment_container, sourceNesFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.ArticlesSource(source = articlesSource.id))
    }


    override fun navigateToSearch(fragmentManager: FragmentManager) {
        val searchFragment = SearchFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, searchFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Search)
    }

    override fun navigateToFilter(fragmentManager: FragmentManager) {
        val filterFragment = FilterFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, filterFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Filter)
    }

    override fun navigateToFilterDate(fragmentManager: FragmentManager) {
        /*  val filterDateFragment = FilterDateFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, filterDateFragment)
            addToBackStack(null)
        }*/
        listener?.onNavigated(Screen.FilterDate)
    }

    override fun navigateArticlesSourceToSources(fragmentManager: FragmentManager) {
        fragmentManager.popBackStack()
        listener?.onNavigated(Screen.Sources)
    }
}