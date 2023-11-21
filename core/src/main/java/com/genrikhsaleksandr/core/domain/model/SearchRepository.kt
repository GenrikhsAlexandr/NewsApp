package com.genrikhsaleksandr.core.domain.model

import com.genrikhsaleksandr.core.presentation.ArticleItemList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

object SearchRepository {
    private val _searchRequest: MutableStateFlow<String> = MutableStateFlow("")
    private val _article: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val allArticles: StateFlow<List<ArticleItemList>> = combine(
        _searchRequest,
        _article
    ) { searchRequest, allArticles ->
        val filteredNews = if (searchRequest.isEmpty()) {
            allArticles
        } else {
            allArticles.filter { article ->
                article.sourceName?.contains(searchRequest, ignoreCase = true) ?: false ||
                        article.title.contains(searchRequest, ignoreCase = true) ||
                        article.content?.contains(searchRequest, ignoreCase = true) ?: false
            }
        }
        filteredNews.map {
            ArticleItemList(
                sourceName = it.sourceName,
                title = it.title,
                urlToImage = it.urlToImage,
                article = it,
                id = it.id
            )
        }
    }.stateIn(CoroutineScope(Dispatchers.IO), SharingStarted.Eagerly, emptyList())


    fun setArticle(article: List<Article>) {
        CoroutineScope(Dispatchers.IO).launch {
            _article.value = article
        }
        _article.value = article
    }

    fun setSearchRequest(request: String) {
        _searchRequest.value = request
        println("setSearchRequest: $request")
    }
}