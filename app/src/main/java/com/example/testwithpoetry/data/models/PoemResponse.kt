package com.example.testwithpoetry.data.models

import com.example.testwithpoetry.domain.models.Poem
import kotlinx.serialization.Serializable

@Serializable
data class PoemResponse(
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: String
) {
    fun toDomain(): Poem {
        return Poem(
            title = title,
            author = author,
            lines = lines,
            lineCount = lineCount
        )
    }
}