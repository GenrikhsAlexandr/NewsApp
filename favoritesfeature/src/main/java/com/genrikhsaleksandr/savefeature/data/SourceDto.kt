package com.genrikhsaleksandr.savefeature.data

import kotlinx.serialization.Serializable

@Serializable
data class SourceDto(
    val id: String,
    val name: String
)