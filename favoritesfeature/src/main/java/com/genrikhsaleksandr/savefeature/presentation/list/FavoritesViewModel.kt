package com.genrikhsaleksandr.savefeature.presentation.list

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.Category
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
                sourceName = it.sourceName,
                title = it.title,
                urlToImage = it.urlToImage,
                article = it,
                id = it.id
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

   fun init (){
        viewModelScope
        viewModelScope.launch {
            try {
                _news.value = interactor.getArticlesListForCategory("${Category.GENERAL}") ?: emptyList()
                println("news = ${_news.value}")
                println("idArticle = ${news.value}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getFavoritesArticle(article: Article): List<Article> {
        return interactor.getFavoritesArticles()
    }

    fun onNewsItemClick(article: Article, fragmentManager: FragmentManager) {
        navigator.navigateToDetailsArticle(article, fragmentManager)
    }
}