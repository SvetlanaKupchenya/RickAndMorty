package com.example.rickandmorty.ui.characters.api

import com.google.gson.annotations.Expose

data class CharacterLocationApi(
    @Expose
    val name: String,
    @Expose
    val url: String
)
