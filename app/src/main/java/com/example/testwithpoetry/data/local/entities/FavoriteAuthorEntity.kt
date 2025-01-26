package com.example.testwithpoetry.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testwithpoetry.domain.models.Author

@Entity(tableName = "favorite_authors")
data class FavoriteAuthorEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "author_name")
    val authorName: String
) {
    fun toDomain(): Author {
        return Author(
            name = authorName,
            isFavorite = true
        )
    }
}