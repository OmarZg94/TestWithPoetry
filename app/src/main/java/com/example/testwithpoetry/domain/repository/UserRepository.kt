package com.example.testwithpoetry.domain.repository

import com.example.testwithpoetry.domain.models.User

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUser(): User?
    suspend fun deleteUser()
}