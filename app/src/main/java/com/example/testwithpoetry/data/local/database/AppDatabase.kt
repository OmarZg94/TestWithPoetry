package com.example.testwithpoetry.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testwithpoetry.data.local.dao.UserDao
import com.example.testwithpoetry.data.local.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}