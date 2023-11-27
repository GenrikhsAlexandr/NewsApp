package com.genrikhsaleksandr.core.presentation.filter

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.FilterRepository
import com.genrikhsaleksandr.core.domain.model.LocaleFilter
import com.genrikhsaleksandr.core.navigation.Navigator
import kotlinx.coroutines.launch
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val filterRepository: FilterRepository,
    private val reducer: FilterReducer
) : ViewModel() {

    private val _state = MutableLiveData<FilterState>()
    val state: LiveData<FilterState> get() = _state

    init {
        viewModelScope.launch {
            filterRepository.date.collect {
                sendEvent(FilterEvent.DateSelected(it))
            }
        }
        viewModelScope.launch {
            filterRepository.languageFilters.collect {
                sendEvent(FilterEvent.LanguagesSelected(it))
            }
        }
        viewModelScope.launch {
            filterRepository.articleTag.collect {
                sendEvent(FilterEvent.ArticleTagSelected(it))
                filterRepository.articleTag.collect {
                    sendEvent(FilterEvent.ArticleTagSelected(it))
                }
            }
        }
    }

    private fun sendEvent(event: FilterEvent) {
        _state.value = reducer.reduceState(_state.value, event)
    }

    fun onNavigationBackFilter(fragment: FragmentManager) {
        navigator.navigateBackFilter(fragment)
    }

    fun onNavigationSavedFilter(fragment: FragmentManager) {
        navigator.navigateSavedFilter(fragment)
    }

    fun setArticleTag(articleTag: ArticleTag) {
        filterRepository.setArticleTag(articleTag)
    }

    fun setDate(date: Pair<Long, Long>) {
        filterRepository.setDate(date)
    }

    fun onLanguageChipClicked(languageFilter: LocaleFilter) {
        filterRepository.setLanguageFilter(languageFilter)
    }
}