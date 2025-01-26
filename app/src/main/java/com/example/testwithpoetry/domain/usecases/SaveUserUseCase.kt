package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.models.User
import com.example.testwithpoetry.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) = userRepository.saveUser(user)
}