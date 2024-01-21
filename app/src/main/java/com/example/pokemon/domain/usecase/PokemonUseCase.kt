package com.example.pokemon.domain.usecase

import com.example.pokemon.domain.entities.Pokemon
import com.example.pokemon.domain.repositories.IPokemonRepositories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IPokemonUseCase {
    suspend fun fetchData() : Flow<Result<Pokemon>>
}

class PokemonUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepositories
) : IPokemonUseCase {

    override suspend fun fetchData() : Flow<Result<Pokemon>> {
        return pokemonRepository.fetchData()
    }
}