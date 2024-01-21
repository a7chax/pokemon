package com.example.pokemon.data

import com.example.pokemon.domain.entities.Pokemon
import com.example.pokemon.domain.repositories.IPokemonRepositories
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRepositories @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource
) :IPokemonRepositories {

    override fun fetchData() : Flow<Result<Pokemon>> {
        return flow {
            remoteDataSource.fetchData()
                .map { result ->
                    if(result.isSuccess) {
                        val data = result.getOrNull()
                        if(data != null) {
                            Result.success(data.toDomain())
                        } else {
                            Result.failure(Exception("Data is null"))
                        }
                    } else {
                        Result.failure(result.exceptionOrNull()!!)
                    }
                }.collect {emit(it)}

        }
    }
}