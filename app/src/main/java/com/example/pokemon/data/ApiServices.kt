package com.example.pokemon.data

import retrofit2.http.GET

interface ApiService {

    @GET("pokemon")
    suspend fun fetchData(): PokemonListResponse // replace with your API endpoint and response type
}