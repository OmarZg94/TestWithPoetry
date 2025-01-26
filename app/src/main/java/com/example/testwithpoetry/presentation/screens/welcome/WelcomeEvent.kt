package com.example.testwithpoetry.presentation.screens.welcome

sealed class WelcomeEvent {
    data class UserChanged(val input: String): WelcomeEvent()
    data class EmailChanged(val input: String): WelcomeEvent()
    data class BirthdayChanged(val input: Long): WelcomeEvent()
    data class ShowDateModal(val visible: Boolean): WelcomeEvent()
    data object SaveUser : WelcomeEvent()
}