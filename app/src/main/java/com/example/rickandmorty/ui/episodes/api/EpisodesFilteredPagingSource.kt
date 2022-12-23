package com.example.rickandmorty.ui.episodes.api

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState

class EpisodesFilteredPagingSource (
    val episodesNetworkSource: EpisodesNetworkSource,
    val options: HashMap<String, String>
): PagingSource<Int, EpisodeApi>() {

    var errorMessage: String = ""

    override fun getRefreshKey(state: PagingState<Int, EpisodeApi>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeApi> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = episodesNetworkSource.fetchFilteredEpisodes(nextPage, options)

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
            errorMessage = "No episodes with such parameters!"
            Log.d("TAG", "errorMessage = $errorMessage")
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}