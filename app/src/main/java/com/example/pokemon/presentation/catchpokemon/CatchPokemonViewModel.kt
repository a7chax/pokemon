package com.example.pokemon.presentation.catchpokemon

import android.util.Log
import androidx.lifecycle.*
import com.example.pokemon.domain.entities.PokemonRequestBody
import com.example.pokemon.domain.repositories.IPokemonRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatchPokemonViewModel @Inject constructor(
    private val pokemonRepositories: IPokemonRepositories
) : ViewModel() {
    private val _catchPokemon: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val catchPokemon: StateFlow<Boolean?> = _catchPokemon

    private val _addPokemon: MutableStateFlow<String?> = MutableStateFlow(null)
    val addPokemonStatus: StateFlow<String?> = _addPokemon

    init {
        viewModelScope.launch {
            pokemonRepositories.catchPokemon().collect {
                if (it.isSuccess) {
                    val catchPokemon = it.getOrNull()
                    if (catchPokemon != null) {
                        _catchPokemon.value = catchPokemon
                    } else {
                        _catchPokemon.value = false
                    }
                }
            }
        }
    }

    fun handleaddPokemon(requestBody: PokemonRequestBody) {
        viewModelScope.launch {
            pokemonRepositories.addPokemon(requestBody).collect {
                if (it.isSuccess) {
                    val addPokemon = it.getOrNull()
                    if (addPokemon != null) {
                        _addPokemon.value = addPokemon
                    } else {
                        _addPokemon.value = "Pokemon is null"
                    }
                }
            }
        }
    }
}