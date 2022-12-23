package com.example.rickandmorty.ui.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.ui.episodes.api.*
import kotlinx.coroutines.flow.Flow

class EpisodesListViewModel() : ViewModel() {

    var episodesNetworkSource: EpisodesNetworkSource

    init {
        episodesNetworkSource = EpisodesRetrofitInstance
            .getEpisodesRetrofitInstance()
            .create(EpisodesNetworkSource::class.java)
    }

    fun getAllEpisodesList(): Flow<PagingData<EpisodeApi>> {
        return Pager (
            config = PagingConfig(pageSize = 3, maxSize = 10),
            pagingSourceFactory = { EpisodesAllPagingSource(episodesNetworkSource) }
        ).flow.cachedIn(viewModelScope)
    }

    fun getFilteredEpisodesList(options: HashMap<String, String>): Flow<PagingData<EpisodeApi>> {
        return Pager (
            config = PagingConfig(pageSize = 3, maxSize = 10),
            pagingSourceFactory = { EpisodesFilteredPagingSource(episodesNetworkSource, options) }
        ).flow.cachedIn(viewModelScope)
    }
}