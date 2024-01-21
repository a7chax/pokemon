package com.example.pokemon.data

import retrofit2.http.*

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(): List<PokemonListResponse> // repl

    @GET("pokemon/catch")
    suspend fun catchPokemon() : Boolean// ace with your API e

    @POST("pokemon/mine")
    suspend fun addPokemon(@Body pokemonRequestBody: PokemonRequest) : String
}