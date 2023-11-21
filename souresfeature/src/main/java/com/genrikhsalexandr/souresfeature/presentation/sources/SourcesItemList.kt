package com.genrikhsalexandr.souresfeature.presentation.sources

import com.genrikhsaleksandr.core.domain.model.Source
import com.genrikhsalexandr.souresfeature.R

data class SourcesItemList(
    val name: String?,
    val category: String?,
    val country: String?,
    val source: Source
) {
    val viewType = R.layout.list_item_sources
}
