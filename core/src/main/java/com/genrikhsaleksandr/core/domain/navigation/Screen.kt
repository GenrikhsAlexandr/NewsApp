package com.genrikhsaleksandr.core.domain.navigation

sealed class Screen {
    data object Headlines : Screen()
    data object Favorites : Screen()

    data class Sources(
        val fragmentName: String
    ) : Screen()

    data object Search : Screen()

    data object Filter : Screen()

    data object DetailArticle : Screen()

    data class ArticlesSource(
        val sourceName: String
    ) : Screen()

    data object FilterDate : Screen()

    data object Default : Screen()
}