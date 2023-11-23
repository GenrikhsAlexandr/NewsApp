package com.genrikhsaleksandr.core.presentation.filter

import androidx.core.util.Pair
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.FilterRepository
import com.genrikhsaleksandr.core.domain.model.LocaleFilter
import com.genrikhsaleksandr.core.navigation.Navigator
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val filterRepository: FilterRepository

) : ViewModel() {

    private val _state = MutableLiveData<FilterViewState>()
    val state: LiveData<FilterViewState> get() = _state

    init {
        _state.value = FilterViewState(
            selectedTag = ArticleTag.POPULAR,
            selectedDate = null,
            selectedLanguage = listOf(
                LocaleFilter.Russian(),
                LocaleFilter.English(),
                LocaleFilter.Deutsch()
            )
        )
    }

    fun onNavigationBackFilter(fragment: FragmentManager) {
        navigator.navigateBackFilter(fragment)
    }

    fun onNavigationSavedFilter(fragment: FragmentManager) {
        navigator.navigateSavedFilter(fragment)
    }

    fun setLanguage(locale: List<LocaleFilter>) {
        filterRepository.setLanguage(locale)
    }

    fun setArticleTag(articleTag: ArticleTag) {
        filterRepository.setArticleTag(articleTag)
    }

    fun setDate(date: Pair<Long, Long>) {
        filterRepository.setDate(date)
    }
}