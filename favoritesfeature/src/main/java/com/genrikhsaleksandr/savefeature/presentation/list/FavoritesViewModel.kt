package com.genrikhsaleksandr.savefeature.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.navigation.Navigator
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

    ) : ViewModel() {

    private val _news: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val news: StateFlow<List<NewsItemList>> = _news.map { news ->
        news.map {
            NewsItemList(
                source = it.source,
                title = it.title,
                urlToImage = it.urlToImage,
                article = it
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope
        navigator.navigateToArticleDetails()
        viewModelScope.launch {
            try {
                _news.value = interactor.getArticlesList() ?: emptyList()
                println("news = ${_news.value}")
                saveFavoritesArticle(_news.value.last())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getFavoritesArticle(article: Article): List<Article> {
        return interactor.getFavoritesArticles()
    }

    private suspend fun saveFavoritesArticle(article: Article) {
        interactor.saveFavoritesArticle(article)
    }

    suspend fun deleteFavoritesArticle(article: Article) {
        interactor.deleteFavoriteArticle(article)
    }
}