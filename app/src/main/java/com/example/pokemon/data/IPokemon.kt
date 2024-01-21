package com.example.pokemon.data

import com.example.pokemon.domain.entities.Pokemon

data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
){
    data class PokemonResult(
        val name: String,
        val url: String
    )
}


fun PokemonListResponse.toDomain() : Pokemon {
    return Pokemon(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toDomain()  }
    )
}

fun PokemonListResponse.PokemonResult.toDomain() : Pokemon.PokemonResult {
    return Pokemon.PokemonResult(
        name = name,
        url = url
    )
}