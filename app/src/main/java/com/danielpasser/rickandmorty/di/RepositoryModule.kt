package com.danielpasser.rickandmorty.di

import com.danielpasser.rickandmorty.api.Api
import com.danielpasser.rickandmorty.repo.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(retrofit: Api) : CharacterRepository =CharacterRepository(retrofit)
}