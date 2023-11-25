package com.genrikhsalexandr.souresfeature.presentation.articlessource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsalexandr.souresfeature.domain.SourcesInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArticlesSourceViewModel @Inject constructor(
    private val interactor: SourcesInteractor
) : ViewModel() {

    private val _articlesSource: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val articlesSource: StateFlow<List<ArticlesSourceItemList>> = _articlesSource.map { news ->
        news.map {
            ArticlesSourceItemList(
                title = it.title,
                sourceName = it.sourceName,
                articlesSource = it,
                urlToImage = it.urlToImage
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun setArticlesId(articlesSourceId: String) {
        viewModelScope.launch {
            _articlesSource.value =
                interactor.getArticlesSourceList(articlesSourceId) ?: emptyList()
        }
    }
}