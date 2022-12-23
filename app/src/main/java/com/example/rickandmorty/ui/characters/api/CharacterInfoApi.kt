package com.example.rickandmorty.ui.characters.api

import com.google.gson.annotations.Expose

data class CharacterInfoApi(
    @Expose
    val count: Int,
    @Expose
    val pages: Int,
    @Expose
    val next: String,
    @Expose
    val prev: String
)
