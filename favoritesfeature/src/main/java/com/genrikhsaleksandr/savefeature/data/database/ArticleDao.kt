package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticle(article: ArticleDbModel)

    @Query("SELECT * FROM news_table")
    fun getArticleFromDb(): List<ArticleDbModel>

    @Delete
    fun deleteArticle(article: ArticleDbModel)
}