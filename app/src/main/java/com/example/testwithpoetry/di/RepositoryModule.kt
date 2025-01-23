package com.example.testwithpoetry.di

import com.example.testwithpoetry.data.repository.PoetryRepositoryImpl
import com.example.testwithpoetry.domain.repository.PoetryRepository
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
}