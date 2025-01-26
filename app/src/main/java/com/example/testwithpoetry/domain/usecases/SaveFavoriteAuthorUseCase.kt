package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.repository.FavoriteAuthorRepository
import javax.inject.Inject

class SaveFavoriteAuthorUseCase @Inject constructor(
    private val favoriteAuthorRepository: FavoriteAuthorRepository
) {
    suspend operator fun invoke(author: Author) =
        favoriteAuthorRepository.saveFavoriteAuthor(author)
}