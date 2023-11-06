package com.genrikhsalexandr.souresfeature.presentation

import com.genrikhsaleksandr.core.domain.model.Sources

data class SourcesItemList(
    val name: String?,
    val category: String?,
    val country:String?,
    val articleSources: Sources
)
