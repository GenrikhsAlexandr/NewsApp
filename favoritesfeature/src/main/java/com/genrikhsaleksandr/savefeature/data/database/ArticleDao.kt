package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.genrikhsaleksandr.core.domain.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: ArticleDbModel):Long

    @Query("SELECT * FROM article")
    suspend fun getArticleFromDb(): List<ArticleDbModel>

    @Delete
    suspend fun deleteArticle(article: ArticleDbModel)
}