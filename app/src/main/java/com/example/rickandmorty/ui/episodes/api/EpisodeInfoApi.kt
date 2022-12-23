package com.example.rickandmorty.ui.episodes.api

import com.google.gson.annotations.Expose

data class EpisodeInfoApi(
    @Expose
    val count: Int,
    @Expose
    val pages: Int,
    @Expose
    val next: String,
    @Expose
    val prev: String
)
