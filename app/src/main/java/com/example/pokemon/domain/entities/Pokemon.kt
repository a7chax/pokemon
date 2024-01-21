package com.example.pokemon.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pokemon(
    val name: String,
    val id: Int,
    val images : String,
    val type: List<String>,
    val moves: List<String>
) :Parcelable

data class PokemonRequestBody(
    val name: String,
    val pokemonName: String,
    val images: String
)
