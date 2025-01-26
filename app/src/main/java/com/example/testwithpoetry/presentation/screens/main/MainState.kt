package com.example.testwithpoetry.presentation.screens.main

import com.example.testwithpoetry.domain.models.User

data class MainState(
    val verificationCompleted: Boolean = false,
    val userVerified: User? = null
)