package com.genrikhsalexandr.detailarticlefeature.presentation

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.Article
import com.genrikhsaleksandr.core.navigation.Navigator
import com.genrikhsalexandr.detailarticlefeature.domain.DetailInteractor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val interactor: DetailInteractor,
    private val navigator: Navigator
) : ViewModel() {

    val isIconClick: MutableStateFlow<Boolean> = MutableStateFlow(false)

    fun onFavoriteButtonClicked() {
        isIconClick.value = !isIconClick.value
        viewModelScope.launch {
            if (isIconClick.value) {
                saveFavoritesArticle(article)
            } else {
                deleteFavoritesArticle(article)
            }
        }
        println("isIconClick = ${isIconClick.value}")
    }

    private lateinit var article: Article


    private val _isFavorite: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun setArticle(article: Article) {
        this.article = article
        viewModelScope.launch {
            _isFavorite.value = interactor.isFavorite(article)
            println("articleId=${article.id}")
        }
    }

    suspend fun saveFavoritesArticle(article: Article) {
        interactor.saveFavoritesArticle(article)
        println("saveArticle = $article")
    }

    suspend fun deleteFavoritesArticle(article: Article) {
        interactor.deleteFavoriteArticle(article)
        println("deleteArticle")

    }

    fun onNavigationBackDetailArticle(fragment: FragmentManager) {
        navigator.navigateBackDetailArticle(fragment)
    }
    fun onNavigationBackDetailArticleForSearch(fragment: FragmentManager) {
        navigator.navigateBackDetailArticleForSearch(fragment)
    }
}