package com.example.rickandmorty.ui.locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmorty.ui.locations.api.*
import kotlinx.coroutines.flow.Flow

class LocationsListViewModel : ViewModel() {

    var locationsNetworkSource: LocationsNetworkSource

    init {
        locationsNetworkSource = LocationsRetrofitInstance
            .getLocationsRetrofitInstance()
            .create(LocationsNetworkSource::class.java)
    }

    fun getAllLocationsList(): Flow<PagingData<LocationApi>> {
        return Pager (
            config = PagingConfig(pageSize = 7, maxSize = 100),
            pagingSourceFactory = { LocationsAllPagingSource(locationsNetworkSource) }
        ).flow.cachedIn(viewModelScope)
    }

    fun getFilteredLocationsList(options: HashMap<String, String>): Flow<PagingData<LocationApi>> {
        return Pager (
            config = PagingConfig(pageSize = 7, maxSize = 100),
            pagingSourceFactory = { LocationsFilteredPagingSource(locationsNetworkSource, options) }
        ).flow.cachedIn(viewModelScope)
    }
}