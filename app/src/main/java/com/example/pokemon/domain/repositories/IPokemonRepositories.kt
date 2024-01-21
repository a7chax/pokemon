package com.example.pokemon.domain.repositories

import com.example.pokemon.domain.entities.Pokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepositories {
    fun fetchData(): Flow<Result<Pokemon>>
}