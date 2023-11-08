package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleDbModel(
    @PrimaryKey (autoGenerate = true)
    val id: Long,
    val publishedAt: String,
    val sourceName: String?,
    val title: String,
    val urlToImage: String?,
    val content: String?,
    val sourceId:String?,
    val url:String
)