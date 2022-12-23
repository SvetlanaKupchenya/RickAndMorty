package com.example.rickandmorty.ui.characters.api

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class CharactersFilteredPagingSource(
    val charactersNetworkSource: CharactersNetworkSource,
    val options: HashMap<String, String>,
): PagingSource<Int, CharacterApi>() {

    var errorMessage: String = ""

    override fun getRefreshKey(state: PagingState<Int, CharacterApi>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterApi> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = charactersNetworkSource.fetchFilteredCharacters(nextPage, options)

            var nextPageNumber: Int? = null
            if (response?.info?.next != null) {
                val uri = Uri.parse(response?.info?.next!!)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            var prevPageNumber: Int? = null
            if(response?.info?.prev != null) {
                val uri = Uri.parse(response?.info?.prev!!)
                val prevPageQuery = uri.getQueryParameter("page")
                prevPageNumber = prevPageQuery?.toInt()
            }

            LoadResult.Page(data = response.results,
                prevKey = prevPageNumber,
                nextKey = nextPageNumber)

        } catch (e: Exception) {
            errorMessage = "No characters with such parameters!"
            Log.d("TAG", "errorMessage = $errorMessage")
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}