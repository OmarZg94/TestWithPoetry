package com.example.testwithpoetry.data.models

import com.example.testwithpoetry.domain.models.Author
import kotlinx.serialization.Serializable

@Serializable
data class AuthorsResponse(
    val authors: List<String>
)

fun String.toDomain(): Author {
    return Author(name = this, isFavorite = false)
}