package com.genrikhsaleksandr.core.presentation.filter

import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.LocaleFilter

sealed interface FilterEvent {
    data class DateSelected(val date: Pair<Long, Long>?) : FilterEvent
    data class LanguagesSelected(val language: List<LocaleFilter>) : FilterEvent
    data class ArticleTagSelected(val articleTag: ArticleTag) : FilterEvent
}