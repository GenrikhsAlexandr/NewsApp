package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsDbModel(
    @PrimaryKey
    val category: String,
    val language: String,
    val source: String,
    val urlToImage: String?,
    val title: String,
    val url: String?,
)
