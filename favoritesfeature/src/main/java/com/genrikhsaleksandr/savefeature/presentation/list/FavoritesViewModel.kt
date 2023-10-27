package com.genrikhsaleksandr.savefeature.presentation.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.savefeature.data.ArticleRepositoryImp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private val _news: MutableStateFlow<List<Article>> = MutableStateFlow(emptyList())
    private val repository = ArticleRepositoryImp(application)

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
        viewModelScope.launch {
            try {
                _news.value = repository.getArticles() ?: emptyList()
                println("news = $news")
                saveFavoritesArticle(_news.value.last())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    suspend fun getFavoritesArticle(article: Article): List<Article> {
        return repository.getFavoritesArticle(article)
    }

    suspend fun saveFavoritesArticle(article: Article) {
        repository.saveFavoritesArticle(article)
    }

    suspend fun deleteFavoritesArticle(article: Article) {
        repository.deleteFavoritesArticle(article)
    }
}