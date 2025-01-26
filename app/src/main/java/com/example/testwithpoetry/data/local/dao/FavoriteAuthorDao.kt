package com.example.testwithpoetry.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testwithpoetry.data.local.entities.FavoriteAuthorEntity

@Dao
interface FavoriteAuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteAuthor(favoriteAuthor: FavoriteAuthorEntity)

    @Query("SELECT * FROM favorite_authors")
    suspend fun getAllFavoriteAuthors(): List<FavoriteAuthorEntity>

    @Query("DELETE FROM favorite_authors")
    suspend fun deleteAllFavoriteAuthors()

    @Query("DELETE FROM favorite_authors WHERE author_name=:authorName")
    suspend fun deleteFavoriteAuthor(authorName: String)
}

