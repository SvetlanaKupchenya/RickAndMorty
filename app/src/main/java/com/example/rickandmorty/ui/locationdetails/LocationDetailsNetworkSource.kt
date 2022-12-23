package com.example.rickandmorty.ui.locationdetails

import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.example.rickandmorty.ui.locations.api.LocationApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationDetailsNetworkSource {
    @GET("location/{id}")
    fun fetchSingleLocation(
        @Path("id") id: Int
    ): Call<LocationApi>

    @GET("character/{ids}")
    fun fetchMultipleCharacters(
        @Path("ids") ids: List<Int>
    ): Call<List<CharacterApi>>
}