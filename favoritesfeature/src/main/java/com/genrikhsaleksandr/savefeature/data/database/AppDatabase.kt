package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleRequestDao(): ArticleDao
}