package com.example.testwithpoetry.domain.repository

import com.example.testwithpoetry.domain.models.Author

interface FavoriteAuthorRepository {
    suspend fun saveFavoriteAuthor(author: Author)
    suspend fun getFavoriteAuthors(): List<Author>
    suspend fun deleteFavoriteAuthor(author: Author)
    suspend fun deleteAllFavoriteAuthors()
}