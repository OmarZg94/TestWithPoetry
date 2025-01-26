package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.repository.FavoriteAuthorRepository
import javax.inject.Inject

class RemoveAllFavoriteAuthorsUseCase @Inject constructor(
    private val favoriteAuthorRepository: FavoriteAuthorRepository
) {
    suspend operator fun invoke() = favoriteAuthorRepository.deleteAllFavoriteAuthors()
}