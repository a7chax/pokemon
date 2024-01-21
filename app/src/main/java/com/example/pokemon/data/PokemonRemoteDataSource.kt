package com.example.pokemon.data

import android.util.Log
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(
    val apiService: ApiService
){
    suspend fun fetchData() : Flow<Result<PokemonListResponse>> {
        return flow {
            try {
                val response = apiService.fetchData()
                emit(Result.success(response))
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }
}