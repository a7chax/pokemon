package com.example.pokemon.data

import com.example.pokemon.domain.entities.*
import com.example.pokemon.domain.repositories.IPokemonRepositories
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRepositories @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
) :IPokemonRepositories {



    override fun fetchData() : Flow<Result<List<Pokemon>>> {
        return flow {
            remoteDataSource.fetchData()
                .map { result ->
                    if(result.isSuccess){
                        val pokemonList = result.getOrNull()
                        if (pokemonList != null) {
                            Result.success(pokemonList.map { it.toDomain() })
                        } else {
                            Result.failure(Exception("Pokemon list is null"))
                        }
                    }else{
                        Result.failure(result.exceptionOrNull()!!)
                    }
                }.collect { emit(it) }
        }
    }

    override fun catchPokemon(): Flow<Result<Boolean>> {
        return flow {
            remoteDataSource.catchPokemon()
                .map { result ->
                    if(result.isSuccess){
                        val catchPokemon = result.getOrNull()
                        if (catchPokemon != null) {
                            Result.success(catchPokemon)
                        } else {
                            Result.failure(Exception("Pokemon is null"))
                        }
                    }else{
                        Result.failure(result.exceptionOrNull()!!)
                    }
                }.collect { emit(it)
            }
        }
    }

    override fun addPokemon(pokemon: PokemonRequestBody): Flow<Result<String>> {
        return flow {
            remoteDataSource.addPokemon(pokemon.toRequestBody())
                .map { result ->
                    if(result.isSuccess){
                        val addPokemon = result.getOrNull()
                        if (addPokemon != null) {
                            Result.success(addPokemon)
                        } else {
                            Result.failure(Exception("Pokemon is null"))
                        }
                    }else{
                        Result.failure(result.exceptionOrNull()!!)
                    }
                }.collect { emit(it)
            }
        }
    }

}