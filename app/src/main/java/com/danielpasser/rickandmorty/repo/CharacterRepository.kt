package com.danielpasser.rickandmorty.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.danielpasser.rickandmorty.api.Api
import com.danielpasser.rickandmorty.model.Character
import com.danielpasser.rickandmorty.paging.CharactersPaging
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val retrofit: Api,
) {

//     suspend fun getAllCharacters(): Flow<PagingData<Character>> = Pager(
//        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
//        pagingSourceFactory = { CharactersPaging(retrofit) }
//    ).flow

    suspend fun getAllCharacters(): Flow<PagingData<Character>> =
        Pager(config = PagingConfig(pageSize = 20, prefetchDistance = 2),
            pagingSourceFactory = { CharactersPaging(retrofit) }
        ).flow
}