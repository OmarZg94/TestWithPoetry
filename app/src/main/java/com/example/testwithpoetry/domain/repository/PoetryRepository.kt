package com.example.testwithpoetry.domain.repository

import com.example.testwithpoetry.data.models.AuthorsResponse
import com.example.testwithpoetry.data.models.PoemResponse
import com.example.testwithpoetry.data.models.PoemTitleReponse
import com.example.testwithpoetry.data.network.NetworkResource

interface PoetryRepository {
    suspend fun getAuths(): NetworkResource<AuthorsResponse>
    suspend fun getTitlesByAuthor(authorName: String): NetworkResource<PoemTitleReponse>
    suspend fun getPoem(authorName: String, title: String): NetworkResource<List<PoemResponse>>
}