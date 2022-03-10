package com.danielpasser.rickandmorty.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danielpasser.rickandmorty.api.Api
import com.danielpasser.rickandmorty.model.Character

class CharactersPaging constructor(
    private val retrofit: Api,
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int=1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val result = retrofit.getCharacters(pageNumber)
            val info = result.info

            var nextPageNumber = if (info.next != null)
                result.info.next.split('=')[1].toInt()
            else null

            var prevPageNumber = if (info.prev != null)
                info.prev.split('=')[1].toInt()
            else null

            LoadResult.Page(
                data = result.results,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
