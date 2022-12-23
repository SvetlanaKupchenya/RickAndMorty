package com.example.rickandmorty.ui.episodes.api

import com.google.gson.annotations.Expose

data class EpisodesApi(
    @Expose
    val info: EpisodeInfoApi,
    @Expose
    val results: List<EpisodeApi>
)
