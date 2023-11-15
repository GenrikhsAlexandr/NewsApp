package com.genrikhsalexandr.searchfeature.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.searchfeature.domain.SearchInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val interactor: SearchInteractor,
    private val navigator: Navigator,

    ) : ViewModel() {
    private val _news: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val news: StateFlow<List<SearchListItem>> = _news.map { news ->
        news.map {
            SearchListItem(
                sourceName = it.sourceName,
                title = it.title,
                urlToImage = it.urlToImage,
                article = it,
                id = it.id
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun onNavigationBackSearch(fragment: FragmentManager) {
        navigator.navigateBackSearch(fragment)
    }
}