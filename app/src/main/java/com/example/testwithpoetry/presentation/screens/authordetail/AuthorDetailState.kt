package com.example.testwithpoetry.presentation.screens.authordetail

import com.example.testwithpoetry.domain.models.PoemTitle
import com.example.testwithpoetry.utils.EMPTY

data class AuthorDetailState(
    val isLoading: Boolean = false,
    val titles: List<PoemTitle>? = null,
    val message: String = EMPTY
)
