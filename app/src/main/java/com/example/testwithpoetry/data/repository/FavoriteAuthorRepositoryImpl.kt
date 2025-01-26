package com.example.testwithpoetry.data.repository

import com.example.testwithpoetry.data.local.dao.FavoriteAuthorDao
import com.example.testwithpoetry.data.local.entities.FavoriteAuthorEntity
import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.repository.FavoriteAuthorRepository
import javax.inject.Inject

class FavoriteAuthorRepositoryImpl @Inject constructor(
    private val favoriteAuthorDao: FavoriteAuthorDao
) : FavoriteAuthorRepository {

    override suspend fun saveFavoriteAuthor(author: Author) {
        val favoriteAuthorEntity = FavoriteAuthorEntity(
            authorName = author.name
        )
        favoriteAuthorDao.insertFavoriteAuthor(favoriteAuthorEntity)
    }

    override suspend fun getFavoriteAuthors(): List<Author> {
        return favoriteAuthorDao.getAllFavoriteAuthors().map { it.toDomain() }
    }

    override suspend fun deleteFavoriteAuthor(author: Author) {
        favoriteAuthorDao.deleteFavoriteAuthor(authorName = author.name)
    }

    override suspend fun deleteAllFavoriteAuthors() {
        favoriteAuthorDao.deleteAllFavoriteAuthors()
    }
}