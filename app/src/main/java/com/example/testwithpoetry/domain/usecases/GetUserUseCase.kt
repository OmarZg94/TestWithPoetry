package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.models.User
import com.example.testwithpoetry.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): User? = repository.getUser()
}