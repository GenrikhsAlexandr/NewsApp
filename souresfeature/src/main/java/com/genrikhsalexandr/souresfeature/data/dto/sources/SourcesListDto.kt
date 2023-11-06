package com.genrikhsalexandr.souresfeature.data.dto.sources

import kotlinx.serialization.Serializable

@Serializable
data class SourcesListDto(
    val status: String?,
    val sources: List<SourcesDto>
)
