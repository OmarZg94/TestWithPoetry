package com.example.testwithpoetry.di

import com.example.testwithpoetry.data.repository.FavoriteAuthorRepositoryImpl
import com.example.testwithpoetry.data.repository.PoetryRepositoryImpl
import com.example.testwithpoetry.data.repository.UserRepositoryImpl
import com.example.testwithpoetry.domain.repository.FavoriteAuthorRepository
import com.example.testwithpoetry.domain.repository.PoetryRepository
import com.example.testwithpoetry.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPoetryRepository(
        impl: PoetryRepositoryImpl
    ): PoetryRepository

    @Binds
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    abstract fun bindFavoriteAuthorRepository(
        impl: FavoriteAuthorRepositoryImpl
    ): FavoriteAuthorRepository
}