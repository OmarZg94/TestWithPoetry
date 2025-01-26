package com.example.testwithpoetry.presentation.screens.authordetail

sealed class AuthorDetailEvent {
    data class GetTitles(val authorName: String) : AuthorDetailEvent()
}