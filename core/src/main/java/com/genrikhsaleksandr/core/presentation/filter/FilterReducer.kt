package com.genrikhsaleksandr.core.presentation.filter

import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.FilterRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class FilterReducer @Inject constructor(
    private val filterRepository: FilterRepository,
    private val articleTag: ArticleTag
) {

    private val dateStartFormatter = SimpleDateFormat("MMM d", Locale.getDefault())
    private val dateEndFormatter = SimpleDateFormat("MMM d yyyy", Locale.getDefault())

    fun reduceState(currentState: FilterState?, event: FilterEvent): FilterState {
        if (currentState == null) {
            val dateValue = filterRepository.date.value
            val selectedDateText = if (dateValue != null) formatDate(dateValue) else null
            return FilterState(
                selectedTag = articleTag,
                selectedDateText = selectedDateText,
                selectedDateValue = FilterRepository.date.value,
                selectedLanguage = emptyList()
            )
        }
        return when (event) {
            is FilterEvent.ArticleTagSelected -> currentState.copy(
                selectedTag = event.articleTag
            )

            is FilterEvent.DateSelected -> currentState.copy(
                selectedDateText = formatDate(event.date),
                selectedDateValue = event.date
            )

            is FilterEvent.LanguagesSelected -> currentState.copy(
                selectedLanguage = event.language
            )
        }
    }

    private fun formatDate(date: Pair<Long, Long>?): String {
        if (date == null) {
            return ""
        }
        val startDate = Date(date.first)
        val endDate = Date(date.second)
        val formattedStartDate = dateStartFormatter.format(startDate)
        val formattedEndDate = dateEndFormatter.format(endDate)
        return "$formattedStartDate - $formattedEndDate"
    }
}