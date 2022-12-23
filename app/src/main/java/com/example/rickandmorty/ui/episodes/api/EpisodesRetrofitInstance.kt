package com.example.rickandmorty.ui.episodes.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodesRetrofitInstance {

    companion object {
        val baseURL = "https://rickandmortyapi.com/api/"

        fun getEpisodesRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}