package com.genrikhsaleksandr.savefeature.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsRequestDao(): NewsDao
}