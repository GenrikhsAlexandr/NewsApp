package com.genrikhsaleksandr.core.presentation.filter

import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.LocaleFilter

sealed class FilterViewIntent {
    data class SelectTag(val tag: ArticleTag) : FilterViewIntent()
    data class SelectDate(val date: ClosedRange<kotlin.time.Duration>?) : FilterViewIntent()
    data class SelectLanguage(val language: List<LocaleFilter>) : FilterViewIntent()
}