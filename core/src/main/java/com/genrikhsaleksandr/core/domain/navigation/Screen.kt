package com.genrikhsaleksandr.core.domain.navigation

sealed class Screen {
    data object Headlines: Screen()
    data object Favorites: Screen()

    data object Sources: Screen()

    data object Search: Screen()

    data object Filter: Screen()

    data object DetailArticle: Screen()

    data class ArticlesSource(
        val source:String?
    ): Screen()

    data object FilterDate: Screen()
}