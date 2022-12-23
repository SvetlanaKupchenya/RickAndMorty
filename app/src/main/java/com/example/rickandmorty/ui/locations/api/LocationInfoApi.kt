package com.example.rickandmorty.ui.locations.api

import com.google.gson.annotations.Expose

data class LocationInfoApi(
    @Expose
    val count: Int,
    @Expose
    val pages: Int,
    @Expose
    val next: String,
    @Expose
    val prev: String
)