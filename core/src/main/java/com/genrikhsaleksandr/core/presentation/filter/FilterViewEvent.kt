package com.genrikhsaleksandr.core.presentation.filter

sealed class FilterViewEvent {
    data object ChipClicked : FilterViewEvent()
    data object SelectLanguage : FilterViewEvent()
    data object ArticleTag : FilterViewEvent()
}