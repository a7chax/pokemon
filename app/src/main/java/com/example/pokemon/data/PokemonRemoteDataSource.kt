package com.example.pokemon.data

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.util.Log
import com.example.pokemon.domain.entities.*
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

    suspend fun getMyPokemon() : Flow<Result<List<MyPokemonResponse>>> {
        return flow {
            try {
                val response = apiService.getMyPokemon()
                emit(Result.success(response))
            } catch (e: Exception) {
                Log.e("PokemonRemoteDataSource", "getMyPokemon: ${e.message}")
                emit(Result.failure(e))
            }
        }
    }

    suspend fun deletePokemon(id: String) : Flow<Result<Boolean>> {
        return flow {
            try {
                val response = apiService.deletePokemon(id)
                Log.d("PokemonRemoteDataSource", "deletePokemon: $response")
                emit(Result.success(response))
            } catch (e: Exception) {
                Log.e("PokemonRemoteDataSource1", "deletePokemon1: ${e.message}")
                emit(Result.failure(e))
            }
        }
    }

    suspend fun updatePokemonName(pokemonRequestUpdateName: PokemonRequestUpdateName) : Flow<Result<Boolean>> {
        return flow {
            try {
                val response = apiService.updatePokemon(pokemonRequestUpdateName)
                Log.d("PokemonRemoteDataSource", "updatePokemon: $response")
                emit(Result.success(response))
            } catch (e: Exception) {
                Log.e("PokemonRemoteDataSource1", "updatePokemon1: ${e.message}")
                emit(Result.failure(e))
            }
        }
    }
}