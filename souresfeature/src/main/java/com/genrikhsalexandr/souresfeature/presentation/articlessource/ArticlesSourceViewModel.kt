package com.genrikhsalexandr.souresfeature.presentation.articlessource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ArticlesSourceViewModel /*@Inject constructor(
    private val interactor: SourcesInteractor
)*/ : ViewModel() {

    private val _articlesSource: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val source: StateFlow<List<ArticlesSourceItemList>> = _articlesSource.map { news ->
        news.map {
            ArticlesSourceItemList(
                title = it.title,
                source = it.source,
                articlesSource = it
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun initArticlesList() {}
    /*init {
        viewModelScope
        viewModelScope.launch {
            try {
                _articlesSource.value = interactor.getArticlesSourceList() ?: emptyList()
                println("source= ${_articlesSource.value}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }*/
}