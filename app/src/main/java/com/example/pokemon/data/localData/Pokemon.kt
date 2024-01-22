package com.example.pokemon.data.localData

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonRequestLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 13L,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "pokemon_name")
    val pokemonName: String,

    @ColumnInfo(name = "image_url")
    val images: String,

    @ColumnInfo(name = "type")
    val type: String = "" // add this line
)