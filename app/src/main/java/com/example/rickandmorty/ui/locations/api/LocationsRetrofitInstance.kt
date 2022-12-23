package com.example.rickandmorty.ui.locations.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationsRetrofitInstance {

    companion object {
        val baseURL = "https://rickandmortyapi.com/api/"

        fun getLocationsRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}