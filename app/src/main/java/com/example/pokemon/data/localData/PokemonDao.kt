package com.example.pokemon.data.localData

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonDao {
    @Insert
    suspend fun insert(pokemon: PokemonRequestLocal)

    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllPokemons(): List<PokemonRequestLocal>
}