package com.example.rickandmorty.ui.characters.api

import com.google.gson.annotations.Expose

data class CharactersApi(
    @Expose
    val info: CharacterInfoApi,
    @Expose
    val results: List<CharacterApi>
)
