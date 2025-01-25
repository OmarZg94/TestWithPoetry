package com.example.testwithpoetry.data.repository

import com.example.testwithpoetry.data.local.dao.UserDao
import com.example.testwithpoetry.data.local.entities.UserEntity
import com.example.testwithpoetry.domain.models.User
import com.example.testwithpoetry.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun saveUser(user: User) {
        val userEntity = UserEntity(
            name = user.name,
            email = user.email,
            birthday = user.birthday
        )
        userDao.insertUser(userEntity)
    }

    override suspend fun getUser(): User? {
        val userEntity = userDao.getFirstUser()
        return userEntity?.toDomain()
    }

    override suspend fun deleteUser(user: User) {
        val userEntity = UserEntity(
            name = user.name,
            email = user.email,
            birthday = user.birthday
        )
        userDao.deleteUser(userEntity)
    }
}