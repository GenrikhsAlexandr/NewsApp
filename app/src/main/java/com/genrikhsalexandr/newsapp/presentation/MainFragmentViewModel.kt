package com.genrikhsalexandr.newsapp.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.domain.navigation.Screen
import com.genrikhsaleksandr.core.navigation.Navigator
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private val _isAppBarVisible: MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isAppBarVisible = _isAppBarVisible

    private val _isNavigationIconVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isNavigationIconVisible = _isNavigationIconVisible

    private val _toolBarTitle: MutableStateFlow<String> = MutableStateFlow("News App")
    val toolBarTitle = _toolBarTitle


    private val navigatorListener: Navigator.Listener = Navigator.Listener { destination: Screen ->
        when (destination) {
            is Screen.Headlines -> {
                isAppBarVisible.value = true
                isNavigationIconVisible.value = false
            }

            is Screen.Favorites -> {
                isAppBarVisible.value = true
                isNavigationIconVisible.value = false

            }

            is Screen.Sources -> {
                _toolBarTitle.value = destination.fragmentName
                isAppBarVisible.value = true
                isNavigationIconVisible.value = false

            }

            is Screen.DetailArticle -> {
                isAppBarVisible.value = false
                isNavigationIconVisible.value = false

            }

            is Screen.Search -> {
                isAppBarVisible.value = false
                isNavigationIconVisible.value = false

            }

            is Screen.Filter -> {
                isAppBarVisible.value = true
                isNavigationIconVisible.value = false

            }

            is Screen.ArticlesSource -> {
                _toolBarTitle.value = destination.sourceName
                isNavigationIconVisible.value = true
                isAppBarVisible.value = true


            }

            is Screen.Default -> {
                isAppBarVisible.value = true
                isNavigationIconVisible.value = false
            }
        }
    }

    init {
        navigator.listener = navigatorListener
    }

    fun onHeadlinesClick(fragment: FragmentManager) {
        navigator.navigateToHeadlines(fragment)
    }

    fun onFavoritesClick(fragment: FragmentManager) {
        navigator.navigateToFavorites(fragment)
    }

    fun onSourcesClick(fragment: FragmentManager) {
        navigator.navigateToSources(fragment)
    }

    fun onSearchClick(fragment: FragmentManager) {
        navigator.navigateToSearch(fragment)
    }

    fun onFilterClick(fragment: FragmentManager) {
        navigator.navigateToFilter(fragment)
    }

    fun onNavigationIconArticlesSourceClick(fragment: FragmentManager) {
        navigator.navigateArticlesSourceToSources(fragment)
    }
}