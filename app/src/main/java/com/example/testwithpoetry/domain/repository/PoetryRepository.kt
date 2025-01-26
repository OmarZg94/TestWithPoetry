package com.example.testwithpoetry.domain.repository

import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.models.Poem
import com.example.testwithpoetry.domain.models.PoemTitle

interface PoetryRepository {
    suspend fun getAuthors(): List<Author>
    suspend fun getTitlesByAuthor(authorName: String): List<PoemTitle>
    suspend fun getPoem(authorName: String, title: String): List<Poem>
}