package com.example.pokemon.data

import retrofit2.http.*

interface ApiService {

    @GET("pokemon")
    suspend fun getAllPokemon(): List<PokemonListResponse> // repl

    @GET("pokemon/catch")
    suspend fun catchPokemon() : Boolean// ace with your API e

    @POST("pokemon/mine")
    suspend fun addPokemon(@Body pokemonRequestBody: PokemonRequest) : String

    @GET("pokemon/mine")
    suspend fun getMyPokemon() : List<MyPokemonResponse>

    @DELETE("pokemon/mine")
    suspend fun deletePokemon(@Query("id") id: String) : Boolean

    @PUT("pokemon/mine")
    suspend fun updatePokemon( @Body pokemonRequestUpdateName: PokemonRequestUpdateName   ) : Boolean
}