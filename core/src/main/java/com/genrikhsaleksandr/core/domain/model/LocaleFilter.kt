package com.genrikhsaleksandr.core.domain.model

import java.util.Locale

sealed class LocaleFilter {
    data class Russian(
        val isEnable: Boolean = false,
        val locale: Locale = Locale("ru")
    ) : LocaleFilter()

    data class English(
        val isEnable: Boolean = false,
        val locale: Locale = Locale.US
    ) : LocaleFilter()

    data class Deutsch(
        val isEnable: Boolean = false,
        val locale: Locale = Locale.GERMAN
    ) : LocaleFilter()

}