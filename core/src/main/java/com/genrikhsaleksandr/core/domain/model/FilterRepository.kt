package com.genrikhsaleksandr.core.domain.model

import androidx.core.util.Pair
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
    private val _date: MutableStateFlow<Pair<Long, Long>?> =
        MutableStateFlow(null)
    private val _articleTag: MutableStateFlow<ArticleTag> = MutableStateFlow(ArticleTag.POPULAR)


    val language: StateFlow<List<LocaleFilter>> = _language
    val date: StateFlow<Pair<Long, Long>?> = _date
    val articleTag: StateFlow<ArticleTag> = _articleTag

    fun setLanguageFilter(filter: LocaleFilter) {
        _language.value = _language.value.map {
            if (filter::class == it::class) {
                filter
            } else {
                it
            }
        }
        println(" _language.value = ${_language.value}")
    }


    fun setDate(date: Pair<Long, Long>?) {
        _date.value = date
        println("_date.value = ${_date.value}")
    }

    fun setArticleTag(articleTag: ArticleTag) {
        _articleTag.value = articleTag
        println(" _articleTag.value = ${_articleTag.value}")

    }
}