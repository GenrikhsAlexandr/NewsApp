package com.genrikhsaleksandr.core.presentation.search

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.SearchRepository
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.core.presentation.ArticleItemList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val navigator: Navigator,
    private val searchRepository: SearchRepository
    ) : ViewModel() {

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    val articles: StateFlow<List<ArticleItemList>> = _articles.map { news ->
        news.map {
            ArticleItemList(
                sourceName = it.sourceName,
                title = it.title,
                urlToImage = it.urlToImage,
                article = it,
                id = it.id
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope.launch {
            searchRepository.filteredArticles.collect {
                _articles.value = it
            }
        }
    }
    fun onSearchQuery(query: String) {
        searchRepository.setSearchRequest(query)
    }

    fun onNavigationBackSearch(fragment: FragmentManager) {
        navigator.navigateBackSearch(fragment)
    }

    fun onArticleItemClick(article: Article, fragment: FragmentManager) {
        navigator.navigateToDetailsArticleForSearch(article, fragment)
    }
}