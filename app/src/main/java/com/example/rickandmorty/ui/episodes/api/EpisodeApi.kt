package com.example.rickandmorty.ui.episodes.api

import com.google.gson.annotations.Expose

data class EpisodeApi(
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val air_date: String,
    @Expose
    val episode: String,
    @Expose
    val characters: List<String>,
    @Expose
    val url: String,
    @Expose
    val created: String
)