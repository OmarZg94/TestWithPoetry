package com.example.testwithpoetry.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AuthorsResponse(
    val authors: List<String>
)