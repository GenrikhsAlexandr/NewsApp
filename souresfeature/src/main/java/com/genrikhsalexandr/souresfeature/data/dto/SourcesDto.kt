package com.genrikhsalexandr.souresfeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SourcesDto(
    val id:String?,
    val name:String?,
    val category: String,
    val description:String?,
    val country: String?
)
