package com.example.pokemon.presentation.releasepokemon

import android.util.Log
import androidx.lifecycle.*
import com.example.pokemon.domain.usecase.PokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReleasePokemonViewModel @Inject constructor(
    val pokemonUseCase: PokemonUseCase
) : ViewModel() {

    val releasePokemnStatus : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val _releasePokemonStatus : StateFlow<Boolean> = releasePokemnStatus


    fun releasePokemon(id: String){
        Log.d("ReleasePokemonViewModel", "releasePokemon: $id")

        viewModelScope.launch {
            pokemonUseCase.deletePokemon(id).collect {
                it.getOrNull()?.let { pokemon ->
                    releasePokemnStatus.value = pokemon
                }
            }
        }
    }
}