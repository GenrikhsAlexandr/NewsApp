package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class ArticleDbModel(
    @PrimaryKey (autoGenerate = false)
    val publishedAt: String,
    val author: String,
    val title: String,
    val urlToImage: String?,
    val content: String?
)
