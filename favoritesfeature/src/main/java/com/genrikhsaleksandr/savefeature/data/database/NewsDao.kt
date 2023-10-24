package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: NewsDbModel)

    @Query("SELECT * FROM News_Table")
    fun getNewsFromDb(): Flow<List<NewsDbModel>>

    @Delete
    fun deleteNews(news: NewsDbModel)
}