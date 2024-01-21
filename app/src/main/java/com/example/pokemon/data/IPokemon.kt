package com.example.pokemon.data

import com.example.pokemon.domain.entities.*

data class PokemonListResponse(
    val name: String,
    val id: Int,
    val images : String,
    val type: List<String>,
    val moves: List<String>
)


fun PokemonListResponse.toDomain() : Pokemon {
    return Pokemon(
        name = name,
        id = id,
        images = images,
        type = type,
        moves = moves
    )
}

data class PokemonRequest(
    val name: String,
    val pokemonName: String,
    val images: String
)

fun PokemonRequestBody.toRequestBody() : PokemonRequest {
    return PokemonRequest(
        name = name,
        pokemonName = name,
        images = images
    )
}
