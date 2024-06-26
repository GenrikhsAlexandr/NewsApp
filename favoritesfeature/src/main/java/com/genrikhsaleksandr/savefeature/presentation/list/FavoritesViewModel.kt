package com.genrikhsaleksandr.savefeature.presentation.list

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.domain.model.SearchRepository
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsaleksandr.core.presentation.ArticleItemList
import com.genrikhsaleksandr.savefeature.domain.FavoritesInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    private val interactor: FavoritesInteractor,
    private val navigator: Navigator,
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val _articles: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val articles: StateFlow<List<ArticleItemList>> = _articles.map { articles ->
        articles.map {
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
           try {
               interactor.getFavoritesArticles().collect {
                   _articles.value = it
               }
           } catch (e: Exception) {
               e.printStackTrace()
           }
       }
       viewModelScope.launch {
            _articles.collect {
                searchRepository.setArticles(it)
            }
        }
    }

    fun onNewsItemClick(article: Article, fragmentManager: FragmentManager) {
        navigator.navigateToDetailsArticle(article, fragmentManager)
    }

    override fun onCleared() {
        super.onCleared()
        searchRepository.setSearchRequest("")
    }
}
