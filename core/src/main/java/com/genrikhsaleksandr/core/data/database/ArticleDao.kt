package com.genrikhsaleksandr.core.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleDbModel):Long

    @Query("SELECT * FROM article")
    fun getArticleFromDb(): Flow<List<ArticleDbModel>>

    @Delete
    suspend fun deleteArticle(article: ArticleDbModel)
}