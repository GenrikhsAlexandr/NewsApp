package com.genrikhsaleksandr.core.presentation.filter

import com.genrikhsaleksandr.core.domain.model.ArticleTag
import com.genrikhsaleksandr.core.domain.model.LocaleFilter

data class FilterState(
    val selectedTag: ArticleTag,
    val selectedDateText: String?,
    val selectedDateValue: Pair<Long, Long>?,
    val selectedLanguage: List<LocaleFilter>
) {

}