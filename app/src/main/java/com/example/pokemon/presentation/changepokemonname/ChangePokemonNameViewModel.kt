package com.example.pokemon.presentation.changepokemonname

import androidx.lifecycle.*
import com.example.pokemon.domain.entities.PokemonUpdateName
import com.example.pokemon.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePokemonNameViewModel @Inject constructor(
    val pokemonUseCase: PokemonUseCase
) : ViewModel(){

    val updatePokemonNameStatus : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val _updatePokemonNameStatus : MutableStateFlow<Boolean> = updatePokemonNameStatus

    fun updatePokemonName(id: String, name: String){
        viewModelScope.launch {
            pokemonUseCase.updatePokemonName(PokemonUpdateName(name,id)).collect {
                it.getOrNull()?.let { pokemon ->
                    updatePokemonNameStatus.value = pokemon
                }
            }
        }
    }
}