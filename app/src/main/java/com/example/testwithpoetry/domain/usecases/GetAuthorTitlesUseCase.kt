package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.models.PoemTitle
import com.example.testwithpoetry.domain.repository.PoetryRepository
import javax.inject.Inject

class GetAuthorTitlesUseCase @Inject constructor(
    private val poetryRepository: PoetryRepository
) {
    suspend operator fun invoke(authorName: String): List<PoemTitle> =
        poetryRepository.getTitlesByAuthor(authorName)
}