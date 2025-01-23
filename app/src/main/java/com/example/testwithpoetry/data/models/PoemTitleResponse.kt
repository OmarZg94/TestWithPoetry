package com.example.testwithpoetry.data.models

import com.example.testwithpoetry.domain.models.PoemTitle
import kotlinx.serialization.Serializable

@Serializable
data class PoemTitleResponse(
    val title: String
) {
    fun toDomain(): PoemTitle {
        return PoemTitle(title)
    }
}