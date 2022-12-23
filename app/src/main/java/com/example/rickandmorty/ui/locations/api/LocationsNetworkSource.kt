package com.example.rickandmorty.ui.locations.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface LocationsNetworkSource {
    @GET("location")
    suspend fun fetchAllLocations(
        @Query("page") page: Int
    ): LocationsApi

    @GET("location")
    suspend fun fetchFilteredLocations(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>
    ): LocationsApi
}