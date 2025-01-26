package com.example.testwithpoetry.domain.models

data class Poem(
    val title: String,
    val author: String,
    val lines: List<String>,
    val lineCount: String
)
