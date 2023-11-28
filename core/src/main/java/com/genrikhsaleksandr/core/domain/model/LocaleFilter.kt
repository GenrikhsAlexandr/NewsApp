package com.genrikhsaleksandr.core.domain.model

import java.util.Locale

sealed interface LocaleFilter {
    val isEnabled: Boolean
    val locale: Locale

    data class Russian(
        override val isEnabled: Boolean = false,
        override val locale: Locale = Locale("ru")
    ) : LocaleFilter

    data class English(
        override val isEnabled: Boolean = false,
        override val locale: Locale = Locale.US
    ) : LocaleFilter

    data class Deutsch(
        override val isEnabled: Boolean = false,
        override val locale: Locale = Locale.GERMAN
    ) : LocaleFilter

}