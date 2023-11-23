package com.genrikhsalexandr.filterfeature.domain

sealed class FilterButton {
    data object None : FilterButton()
    data object Popular : FilterButton()
    data object News : FilterButton()
    data object Relevant : FilterButton()
}
