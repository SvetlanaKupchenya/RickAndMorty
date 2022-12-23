package com.example.rickandmorty.ui.episodedetails

import com.example.rickandmorty.ui.characters.api.CharacterApi
import com.example.rickandmorty.ui.episodes.api.EpisodeApi
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeDetailsNetworkSource {
    @GET("episode/{id}")
    fun fetchSingleEpisode(
        @Path("id") id: Int
    ): Call<EpisodeApi>

    @GET("character/{ids}")
    fun fetchMultipleCharacters(
        @Path("ids") ids: List<Int>
    ): Call<List<CharacterApi>>
}