package com.genrikhsalexandr.newsapp.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.savefeature.presentation.list.FavoritesFragment
import javax.inject.Inject

class MainFragmentViewModel @Inject constructor(
    private val navigator: Navigator
):ViewModel() {
        fun onClickFavorites(favoritesFragment: FragmentManager){
            navigator.navigateToFavorites(favoritesFragment)
        }

    fun onClickHeadlines(headlinesFragment: FragmentManager){
        navigator.navigateToHeadlines(headlinesFragment)
    }

    fun onClickSources(sourcesFragment: FragmentManager){
        navigator.navigateToSources(sourcesFragment)
    }

}