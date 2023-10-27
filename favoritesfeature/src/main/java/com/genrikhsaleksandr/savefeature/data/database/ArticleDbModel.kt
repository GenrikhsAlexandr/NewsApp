package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleDbModel(
    @PrimaryKey
    val publishedAt: String,
    val source: String,
    val title: String,
    val urlToImage: String?,
    val content: String?
)