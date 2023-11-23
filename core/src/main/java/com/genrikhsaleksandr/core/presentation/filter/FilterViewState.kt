package com.genrikhsaleksandr.core.presentation.filter

import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.LocaleFilter

data class FilterViewState(
    val selectedTag: ArticleTag,
    val selectedDate: ClosedRange<kotlin.time.Duration>?,
    val selectedLanguage: List<LocaleFilter>
)