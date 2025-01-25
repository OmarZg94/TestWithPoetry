package com.example.testwithpoetry.domain.models

import com.example.testwithpoetry.utils.EMPTY

data class User (
    val name: String = EMPTY,
    val email: String = EMPTY,
    val birthday: Long = 0L
)