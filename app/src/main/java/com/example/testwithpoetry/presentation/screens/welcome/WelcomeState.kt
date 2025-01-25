package com.example.testwithpoetry.presentation.screens.welcome

import com.example.testwithpoetry.utils.EMPTY

data class WelcomeState(
    val name: String = EMPTY,
    val email: String = EMPTY,
    val birthday: Long = 0L,
    val showModal: Boolean = false,
    val isFormValid: Boolean = false,
)