package com.example.rickandmorty.ui.locations.api

import com.google.gson.annotations.Expose

data class LocationsApi(
    @Expose
    val info: LocationInfoApi,
    @Expose
    val results: List<LocationApi>
)
