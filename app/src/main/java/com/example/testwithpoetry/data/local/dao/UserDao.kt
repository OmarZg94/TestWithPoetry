package com.example.testwithpoetry.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testwithpoetry.data.local.entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users LIMIT 1")
    suspend fun getFirstUser(): UserEntity?

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}