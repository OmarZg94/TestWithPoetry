package com.example.testwithpoetry.domain.usecases

import com.example.testwithpoetry.domain.repository.UserRepository
import javax.inject.Inject

class RemoveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.deleteUser()
}