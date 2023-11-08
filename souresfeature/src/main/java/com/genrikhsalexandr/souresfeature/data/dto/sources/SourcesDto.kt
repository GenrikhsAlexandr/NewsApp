package com.genrikhsalexandr.souresfeature.data.dto.sources

import kotlinx.serialization.Serializable

@Serializable
data class SourcesDto(
    val id:String?,
    val name:String?,
    val category: String,
    val country: String?,
    val language:String,
)