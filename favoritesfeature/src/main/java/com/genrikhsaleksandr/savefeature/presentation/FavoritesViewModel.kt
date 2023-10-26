package com.genrikhsaleksandr.savefeature.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.savefeature.data.ArticleRepositoryImp
import com.genrikhsaleksandr.savefeature.domain.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val _news: MutableStateFlow<List<News>> = MutableStateFlow(emptyList())
    private val repository = ArticleRepositoryImp(application)

    val news: StateFlow<List<NewsItemList>> = _news.map { news ->
        news.map {
            NewsItemList(
                author = it.author,
                title = it.title,
                urlToImage = it.urlToImage,
                news = it
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    init {
        viewModelScope.launch {
            val news = repository.getArticles()
            println("news = $news")
        }
    }
}