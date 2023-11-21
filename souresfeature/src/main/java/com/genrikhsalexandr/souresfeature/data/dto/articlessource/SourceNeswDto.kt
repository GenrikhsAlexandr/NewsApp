package com.genrikhsalexandr.souresfeature.data.dto.articlessource

import kotlinx.serialization.Serializable

@Serializable
data class SourceArticlesDto(
    val id: String,
    val name: String?,
)
