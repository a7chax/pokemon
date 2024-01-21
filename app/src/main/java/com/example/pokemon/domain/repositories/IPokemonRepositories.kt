package com.example.pokemon.domain.repositories

import com.example.pokemon.domain.entities.*
import kotlinx.coroutines.flow.Flow

interface IPokemonRepositories {
    fun fetchData(): Flow<Result<List<Pokemon>>>
    fun catchPokemon(): Flow<Result<Boolean>>
    fun addPokemon(pokemon: PokemonRequestBody): Flow<Result<String>>
}