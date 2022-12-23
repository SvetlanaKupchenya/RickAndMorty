package com.example.rickandmorty.ui.characters.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersRetrofitInstance {

    companion object {
        val baseURL = "https://rickandmortyapi.com/api/"

        fun getCharactersRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}