package com.genrikhsalexandr.detailarticlefeature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsalexandr.detailarticlefeature.domain.DetailInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor,
) : ViewModel() {

    val isIconClick: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onFavoriteButtonClicked() {
        isIconClick.value = !isIconClick.value
    }

    private lateinit var article:Article


    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun setArticle(article: Article) {
       this.article = article
        viewModelScope.launch {
            _isFavorite.value = interactor.isFavorite(article)
        }
    }

    suspend fun saveFavoritesArticle(article: Article) {
        interactor.saveFavoritesArticle(article)
        println("saveArticle")
    }

    suspend fun deleteFavoritesArticle(article: Article) {
        interactor.deleteFavoriteArticle(article)
        println("delArticle")

    }
}