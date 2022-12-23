package com.example.rickandmorty.ui.characters.api

import com.google.gson.annotations.Expose

data class CharacterApi(
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val status: String,
    @Expose
    val species: String,
    @Expose
    val type: String,
    @Expose
    val gender: String,
    @Expose
    val origin: CharacterOriginApi,
    @Expose
    val location: CharacterLocationApi,
    @Expose
    val image: String,
    @Expose
    val episode: List<String>,
    @Expose
    val url: String,
    @Expose
    val created: String
)
