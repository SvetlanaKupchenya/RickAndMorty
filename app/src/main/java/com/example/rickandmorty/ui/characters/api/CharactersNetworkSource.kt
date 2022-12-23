package com.example.rickandmorty.ui.characters.api

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface CharactersNetworkSource {
    @GET("character")
    suspend fun fetchAllCharacters(
        @Query("page") page: Int
    ): CharactersApi

    @GET("character")
    suspend fun fetchFilteredCharacters(
        @Query("page") page: Int,
        @QueryMap options: Map<String, String>
    ): CharactersApi
}