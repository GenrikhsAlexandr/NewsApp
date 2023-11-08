package com.genrikhsalexandr.souresfeature.presentation.sources

import com.genrikhsaleksandr.core.domain.model.Source

data class SourcesItemList(
    val name: String?,
    val category: String?,
    val country:String?,
    val source: Source
)
