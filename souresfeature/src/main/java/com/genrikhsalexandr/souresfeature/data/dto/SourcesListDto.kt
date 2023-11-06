package com.genrikhsalexandr.souresfeature.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SourcesListDto(
    val status: String?,
    val sources: List<SourcesDto>
)
