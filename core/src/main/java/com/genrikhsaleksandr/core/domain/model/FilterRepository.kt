package com.genrikhsaleksandr.core.domain.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object FilterRepository {
    private val _language: MutableStateFlow<List<LocaleFilter>> = MutableStateFlow(
        listOf(
            LocaleFilter.Russian(),
            LocaleFilter.English(),
            LocaleFilter.Deutsch()
        )
    )
    private val _date: MutableStateFlow<CharSequence?> =
        MutableStateFlow(null)
    private val _articleTag: MutableStateFlow<ArticleTag> = MutableStateFlow(ArticleTag.POPULAR)


    val language: StateFlow<List<LocaleFilter>> = _language
    val date: StateFlow<CharSequence?> = _date
    val articleTag: StateFlow<ArticleTag> = _articleTag


    fun setLanguage(locale: List<LocaleFilter>) {
        _language.value = locale
        println(" _language.value = ${_language.value}")

    }

    fun setDate(date: CharSequence) {
        _date.value = date
        println("_date.value = ${_date.value}")
    }

    fun setArticleTag(articleTag: ArticleTag) {
        _articleTag.value = articleTag
        println(" _articleTag.value = ${_articleTag.value}")

    }
}