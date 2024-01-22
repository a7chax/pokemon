package com.example.pokemon.presentation.mypokemon

import android.util.Log
import androidx.lifecycle.*
import com.example.pokemon.domain.entities.MyPokemon
import com.example.pokemon.domain.repositories.IPokemonRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(
    private val pokemonRepositories: IPokemonRepositories
) : ViewModel() {

    val _myPokemon : MutableStateFlow<List<MyPokemon>> = MutableStateFlow(emptyList())
    val myPokemon : StateFlow<List<MyPokemon>> = _myPokemon

    init {
        viewModelScope.launch {
            pokemonRepositories.getMyPokemon().collect {
                Log.d("MyPokemonViewModel", "getMyPokemon: $it")
                _myPokemon.value = it.getOrNull() ?: emptyList()
            }
        }

    }
}