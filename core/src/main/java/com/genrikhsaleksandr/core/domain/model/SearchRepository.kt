package com.genrikhsaleksandr.core.domain.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

object SearchRepository {
    private val _searchRequest: MutableStateFlow<String> = MutableStateFlow("")
    private val _allArticles: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())

    val filteredArticles: StateFlow<List<Article>> = combine(
        _searchRequest,
        _allArticles
    ) { searchRequest, allArticles ->
        if (searchRequest.isEmpty()) {
            allArticles
        } else {
            allArticles.filter { article ->
                article.sourceName?.contains(searchRequest, ignoreCase = true) ?: false ||
                        article.title.contains(searchRequest, ignoreCase = true) ||
                        article.content?.contains(searchRequest, ignoreCase = true) ?: false
            }
        }
    }.stateIn(CoroutineScope(Dispatchers.IO), SharingStarted.Eagerly, emptyList())

    fun setArticles(article: List<Article>) {
        _allArticles.value = article
    }

    fun setSearchRequest(request: String) {
        _searchRequest.value = request
    }
}