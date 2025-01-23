package com.example.testwithpoetry.data.repository

import com.example.testwithpoetry.data.models.AuthorsResponse
import com.example.testwithpoetry.data.models.PoemResponse
import com.example.testwithpoetry.data.models.PoemTitleResponse
import com.example.testwithpoetry.data.models.toDomain
import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.domain.models.Poem
import com.example.testwithpoetry.domain.models.PoemTitle
import com.example.testwithpoetry.domain.repository.PoetryRepository
import com.example.testwithpoetry.utils.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class PoetryRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : PoetryRepository {

    override suspend fun getAuthors(): List<Author> {
        return safeApiCall {
            val response = client.get("https://poetrydb.org/author")
            response.body<AuthorsResponse>().authors.map { it.toDomain() }
        }
    }

    override suspend fun getTitlesByAuthor(authorName: String): PoemTitle {
        return safeApiCall {
            val response = client.get("https://poetrydb.org/author/$authorName/title")
            response.body<PoemTitleResponse>().toDomain()
        }
    }

    override suspend fun getPoem(
        authorName: String,
        title: String
    ): List<Poem> {
        return safeApiCall {
            val response = client.get("https://poetrydb.org/author,title/$authorName;$title")
            response.body<List<PoemResponse>>().map { it.toDomain() }
        }
    }
}