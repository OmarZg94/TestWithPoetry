package com.example.testwithpoetry.presentation.screens.authordetail

sealed class AuthorDetailEvent {
    data class GetTitles(val authorName: String) : AuthorDetailEvent()
    data class GetPoem(val authorName: String, val title: String) : AuthorDetailEvent()
    data object ClosePoemDialog : AuthorDetailEvent()
}