package com.genrikhsalexandr.searchfeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val id: String?,
    val name: String
)