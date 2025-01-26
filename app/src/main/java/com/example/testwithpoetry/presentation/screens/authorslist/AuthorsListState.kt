package com.example.testwithpoetry.presentation.screens.authorslist

import com.example.testwithpoetry.domain.models.Author
import com.example.testwithpoetry.utils.EMPTY

data class AuthorsListState(
    val isLoading: Boolean = false,
    val authors: List<Author>? = null,
    val error: String = EMPTY
)
