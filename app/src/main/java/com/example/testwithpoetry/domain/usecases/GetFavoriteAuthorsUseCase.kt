package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.repository.FavoriteAuthorRepository
import jakarta.inject.Inject

class GetFavoriteAuthorsUseCase @Inject constructor(
    private val favoriteAuthorRepository: FavoriteAuthorRepository
) {
    suspend operator fun invoke(): List<Author> = favoriteAuthorRepository.getFavoriteAuthors()
}