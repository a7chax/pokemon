package com.example.pokemon.domain.entities

data class Pokemon(
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

