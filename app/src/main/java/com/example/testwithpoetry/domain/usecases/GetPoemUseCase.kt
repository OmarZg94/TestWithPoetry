package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.models.Poem
import com.example.testwithpoetry.domain.repository.PoetryRepository
import javax.inject.Inject

class GetPoemUseCase @Inject constructor(
    private val poetryRepository: PoetryRepository
) {
    suspend operator fun invoke(authorName: String, title: String): List<Poem> =
        poetryRepository.getPoem(
            authorName = authorName,
            title = title
        )
}