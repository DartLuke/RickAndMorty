package com.danielpasser.rickandmorty.di

import com.danielpasser.rickandmorty.api.Api
import com.danielpasser.rickandmorty.repo.CharacterRepository
import com.danielpasser.rickandmorty.repo.Repository
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
    fun provideRepository(retrofit: Api) : Repository =Repository(retrofit)

    @Singleton
    @Provides
    fun provideCharacterRepository(retrofit: Api) : CharacterRepository =CharacterRepository(retrofit)
}