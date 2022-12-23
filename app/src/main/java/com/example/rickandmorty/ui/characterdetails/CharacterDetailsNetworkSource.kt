package com.example.rickandmorty.ui.characterdetails

import com.example.rickandmorty.ui.characters.api.CharacterApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterDetailsNetworkSource {
    @GET("character/{id}")
    fun fetchSingleCharacter(
        @Path ("id") id: Int
    ): Call<CharacterApi>
}