package com.genrikhsaleksandr.core.presentation.filter

import android.icu.util.Calendar
import androidx.core.util.Pair
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.FilterRepository
import com.genrikhsaleksandr.core.domain.model.LocaleFilter
import com.genrikhsaleksandr.core.navigation.Navigator
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val filterRepository: FilterRepository

) : ViewModel() {

    private val _state = MutableLiveData<FilterViewState>()
    val state: LiveData<FilterViewState> get() = _state

    private var dateFormat: String? = null

    init {
        viewModelScope.launch {
            combine(
                filterRepository.articleTag,
                filterRepository.date,
                filterRepository.language
            ) { articleTag, mapDate, language ->
                FilterViewState(
                    selectedTag = articleTag,
                    selectedDate = dateFormat,
                    selectedLanguage = language
                )
            }.collect {
                _state.value = it
            }
        }
        viewModelScope.launch {
            filterRepository.date.collect {
                if (it != null) {
                    val startDateInMillis = FilterRepository.date.value?.first ?: 0L
                    val endDateInMillis = FilterRepository.date.value?.second ?: 0L
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = startDateInMillis
                    val startDate = calendar.time
                    calendar.timeInMillis = endDateInMillis
                    val endDate = calendar.time
                    val dateStartFormatter = SimpleDateFormat("MMM d", Locale.getDefault())
                    val dateEndFormatter = SimpleDateFormat("MMM d yyyy", Locale.getDefault())
                    val formattedStartDate = dateStartFormatter.format(startDate)
                    val formattedEndDate = dateEndFormatter.format(endDate)
                    dateFormat = "$formattedStartDate - $formattedEndDate"
                }
            }
        }
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