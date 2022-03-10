package com.danielpasser.rickandmorty.repo


import android.util.Log
import com.danielpasser.rickandmorty.api.Api
import com.danielpasser.rickandmorty.model.Response
import com.danielpasser.rickandmorty.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class Repository constructor(
    private val retrofit: Api,
) {
    suspend fun getCharacterResponse(): Flow<DataState<Response>> = flow {
        emit(DataState.Loading)
        try {
            val charResponse = downloadCharacters()
            emit(DataState.Success(charResponse))
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            emit(DataState.Error(e))
        }
    }

    private suspend fun downloadCharacters(): Response {
        return retrofit.getCharacters(page = 1)
    }



}