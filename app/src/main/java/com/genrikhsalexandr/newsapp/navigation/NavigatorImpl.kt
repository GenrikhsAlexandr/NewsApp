package com.genrikhsalexandr.newsapp.navigation

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsaleksandr.core.domain.navigation.Screen
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.core.presentation.search.SearchFragment
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesFragment
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailForSearchFragment
import com.genrikhsalexandr.detailarticlefeature.presentation.DetailFragment
import com.genrikhsalexandr.filterfeature.presentation.FilterFragment
import com.genrikhsalexandr.headlinesfeature.presentation.HeadlinesFragment
import com.genrikhsalexandr.newsapp.R
import com.genrikhsalexandr.souresfeature.presentation.articlessource.ArticlesSourceFragment
import com.genrikhsalexandr.souresfeature.presentation.sources.SourcesFragment

class NavigatorImpl : Navigator {


    override var listener: Navigator.Listener? = null
    override fun navigateToDetailsArticle(article: Article, fragmentManager: FragmentManager) {
        val detailFragment = DetailFragment.newInstance(article)
        fragmentManager.commit {
            replace(R.id.fragmentContainer, detailFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.DetailArticle)
    }

    override fun navigateToFavorites(fragmentManager: FragmentManager) {
        val favoritesFragment = FavoritesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragmentContainer, favoritesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Favorites)
    }

    override fun navigateToHeadlines(fragmentManager: FragmentManager) {
        val headlinesFragment = HeadlinesFragment.newInstance()
         fragmentManager.commit {
             replace(R.id.fragmentContainer, headlinesFragment)
             addToBackStack(null)
         }
        listener?.onNavigated(Screen.Headlines)
    }

    override fun navigateToSources(fragmentManager: FragmentManager) {
        val sourcesFragment = SourcesFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragmentContainer, sourcesFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Sources("Source"))
    }

    override fun navigateToArticlesSource(
        articlesSource: Source,
        fragmentManager: FragmentManager
    ) {
        val articlesSourceFragment = ArticlesSourceFragment.newInstance(articlesSource)
        fragmentManager.commit {
            replace(R.id.fragmentContainer, articlesSourceFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.ArticlesSource(articlesSource.sourceName))
    }


    override fun navigateToSearch(fragmentManager: FragmentManager) {
        val searchFragment = SearchFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragmentContainer, searchFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.Search)
    }

    override fun navigateToFilter(fragmentManager: FragmentManager) {
        val filterFragment = FilterFragment.newInstance()
        fragmentManager.commit {
            replace(R.id.fragmentContainer, filterFragment)
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
        listener?.onNavigated(Screen.Sources("Source"))
    }

    override fun navigateBackSearch(fragmentManager: FragmentManager) {
        fragmentManager.popBackStack()
        listener?.onNavigated(Screen.Default)
    }

    override fun navigateBackDetailArticle(fragmentManager: FragmentManager) {
        fragmentManager.popBackStack()
        listener?.onNavigated(Screen.Default)
    }

    override fun navigateBackDetailArticleForSearch(fragmentManager: FragmentManager) {
        fragmentManager.popBackStack()
        listener?.onNavigated(Screen.Search)    }

    override fun navigateToDetailsArticleForSearch(article: Article, fragmentManager: FragmentManager) {
        val detailForSearchFragment = DetailForSearchFragment.newInstance(article)
        fragmentManager.commit {
            replace(R.id.fragmentContainer, detailForSearchFragment)
            addToBackStack(null)
        }
        listener?.onNavigated(Screen.DetailArticle)
    }
}