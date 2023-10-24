package com.genrikhsaleksandr.savefeature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.savefeature.data.NewsRepository
import com.genrikhsaleksandr.savefeature.domain.News
import com.genrikhsaleksandr.savefeature.domain.NewsItemList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class FavoritesViewModel : ViewModel() {

    private val _news: MutableStateFlow<List<News>> = MutableStateFlow(emptyList())
    private val repository = NewsRepository()
    val news: StateFlow<List<NewsItemList>> = _news.map { news ->
        news.map {
            NewsItemList(
                sourceName = it.sourceName,
                title = it.title,
                urlToImage = it.urlToImage,
                news = it
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())
}