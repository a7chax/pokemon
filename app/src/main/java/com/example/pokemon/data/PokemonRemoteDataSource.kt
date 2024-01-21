package com.example.pokemon.data

import android.util.Log
import com.example.pokemon.domain.entities.PokemonRequestBody
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    val apiService: ApiService
){
    suspend fun fetchData() : Flow<Result<List<PokemonListResponse>>> {
        return flow {
            try {
                val response = apiService.getAllPokemon()
                emit(Result.success(response))
            } catch (e: Exception) {
                Log.e("PokemonRemoteDataSource", "fetchData: ${e.message}")
                emit(Result.failure(e))
            }
        }
    }

    suspend fun catchPokemon() : Flow<Result<Boolean>> {
        return flow {
            try {
                val response = apiService.catchPokemon()
                emit(Result.success(response))
            } catch (e: Exception) {
                Log.e("PokemonRemoteDataSource", "catchPokemon: ${e.message}")
                emit(Result.failure(e))
            }
        }
    }

    suspend fun addPokemon(pokemonRequestBody: PokemonRequest) : Flow<Result<String>> {
        return flow {
            try {
                val response = apiService.addPokemon(pokemonRequestBody)
                emit(Result.success(response))
            } catch (e: Exception) {
                Log.e("PokemonRemoteDataSource", "addPokemon: ${e.message}")
                emit(Result.failure(e))
            }
        }
    }
}