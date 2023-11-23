package com.genrikhsaleksandr.core.domain.model

import androidx.core.util.Pair
import kotlinx.coroutines.flow.MutableStateFlow

object FilterRepository {
    private val _language: MutableStateFlow<List<LocaleFilter>> = MutableStateFlow(
        listOf(
            LocaleFilter.Russian(),
            LocaleFilter.English(),
            LocaleFilter.Deutsch()
        )
    )
    private val _date: MutableStateFlow<Pair<Long, Long>?> =
        MutableStateFlow(null)
    private val _articleTag: MutableStateFlow<ArticleTag> = MutableStateFlow(ArticleTag.POPULAR)

    fun setLanguage(locale: List<LocaleFilter>) {
        _language.value = locale
    }

    fun setDate(date: Pair<Long, Long>) {
        _date.value = date
        println("_date.value = ${_date.value}")
    }

    fun setArticleTag(articleTag: ArticleTag) {
        _articleTag.value = articleTag
    }
}