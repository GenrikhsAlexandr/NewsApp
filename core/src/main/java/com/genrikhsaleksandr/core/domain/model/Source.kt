package com.genrikhsaleksandr.core.domain.model

data class Source(
    val sourceId: String?,
    val sourceName: String,
    val category: String?,
    val country: String?,
    val language: String
)