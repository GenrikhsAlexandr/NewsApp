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
        val detailFragment = DetailFragment.newInstance()
        fragmentManager.commit {
            add(R.id.fragment_container, detailFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.DETAIL_ARTICLE)
    }

    override fun navigateToFavorites(fragmentManager: FragmentManager) {
        val favoritesFragment = FavoritesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, favoritesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.FAVORITES)
    }

    override fun navigateToHeadlines(fragmentManager: FragmentManager) {
        val headlinesFragment = HeadlinesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, headlinesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.HEADLINES)
    }

    override fun navigateToSources(fragmentManager: FragmentManager) {
        val sourcesFragment = SourcesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, sourcesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.SOURCES)
    }

    override fun navigateToSourceArticles(source: Source, fragmentManager: FragmentManager) {
        val sourceNesFragment = ArticlesSourceFragment.newInstance()
        fragmentManager.commit {
            add(R.id.fragment_container, sourceNesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.SOURCES)
    }

    override fun navigateToSearch(fragmentManager: FragmentManager) {
        val searchFragment = SearchFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, searchFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.SEARCH)
    }

    override fun navigateToFilter(fragmentManager: FragmentManager) {
        val filterFragment = FilterFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, filterFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.FILTER)
    }

   override fun navigateToFilterDate(fragmentManager: FragmentManager) {
      /*  val filterDateFragment = FilterDateFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragment_container, filterDateFragment)
            addToBackStack(null)
        }*/
        listener?.onNavigated(Screen.FILTER)    }
}