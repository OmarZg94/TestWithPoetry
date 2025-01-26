package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.repository.PoetryRepository
import javax.inject.Inject

class GetAuthorsUseCase @Inject constructor(
    private val repository: PoetryRepository
) {
    suspend operator fun invoke(): List<Author> = repository.getAuthors()
}