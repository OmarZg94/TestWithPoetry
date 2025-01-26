package com.example.testwithpoetry.presentation.screens.authorslist

import com.example.testwithpoetry.domain.models.Author

sealed class AuthorsListEvent {
    data object GetAuthorsList : AuthorsListEvent()
    data class SaveFavoriteAuthor(val author: Author): AuthorsListEvent()
    data class RemoveFavoriteAuthor(val author: Author): AuthorsListEvent()
}