package com.example.rickandmorty.ui.episodes.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface EpisodesNetworkSource {

    @GET("episode")
    suspend fun fetchAllEpisodes(
        @Query("page") page: Int
    ): EpisodesApi

    @GET("episode")
    suspend fun fetchFilteredEpisodes(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>
    ): EpisodesApi
}