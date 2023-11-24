package com.genrikhsaleksandr.core.presentation.filter

import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.LocaleFilter
import kotlinx.coroutines.flow.StateFlow

data class FilterViewState(
    val selectedTag: StateFlow<ArticleTag>,
    val selectedDate: StateFlow<CharSequence?>,
    val selectedLanguage: StateFlow<List<LocaleFilter>>
)