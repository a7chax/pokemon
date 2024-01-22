package com.example.pokemon.domain.usecase

import com.example.pokemon.domain.entities.*
import com.example.pokemon.domain.repositories.IPokemonRepositories
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IPokemonUseCase {
    suspend fun fetchData() : Flow<Result<List<Pokemon>>>
    suspend fun catchPokemon() : Flow<Result<Boolean>>
    suspend fun addPokemon(pokemon: PokemonRequestBody): Flow<Result<String>>
    suspend fun getMyPokemon() : Flow<Result<List<MyPokemon>>>

    suspend fun deletePokemon(id: String): Flow<Result<Boolean>>

    suspend fun updatePokemonName(pokemonUpdateName: PokemonUpdateName): Flow<Result<Boolean>>
}

class PokemonUseCase @Inject constructor(
    private val pokemonRepository: IPokemonRepositories
) : IPokemonUseCase {

    override suspend fun fetchData() : Flow<Result<List<Pokemon>>> {
        return pokemonRepository.fetchData()
    }

    override suspend fun catchPokemon() : Flow<Result<Boolean>> {
        return pokemonRepository.catchPokemon()
    }

    override suspend fun addPokemon(pokemon: PokemonRequestBody): Flow<Result<String>> {
        return pokemonRepository.addPokemon(pokemon)
    }

    override suspend fun getMyPokemon(): Flow<Result<List<MyPokemon>>> {
        return pokemonRepository.getMyPokemon()
    }

    override suspend fun deletePokemon(id: String): Flow<Result<Boolean>> {
        return pokemonRepository.deletePokemon(id)
    }

    override suspend fun updatePokemonName(pokemonUpdateName: PokemonUpdateName): Flow<Result<Boolean>> {
        return pokemonRepository.updatePokemonName(pokemonUpdateName)
    }
}