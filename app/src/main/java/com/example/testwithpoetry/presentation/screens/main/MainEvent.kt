package com.example.testwithpoetry.presentation.screens.main

sealed class MainEvent {
    data object ValidateUser : MainEvent()
}