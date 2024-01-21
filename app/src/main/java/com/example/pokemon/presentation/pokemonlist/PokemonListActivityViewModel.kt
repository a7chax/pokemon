package com.example.pokemon.presentation.pokemonlist

import android.util.Log
import androidx.lifecycle.*
import com.example.pokemon.domain.entities.Pokemon
import com.example.pokemon.domain.usecase.*
import dagger.hilt.android.ViewModelLifecycle
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListActivityViewModel @Inject constructor(
    val pokemonUseCase: PokemonUseCase
) : ViewModel() {
    private val _fetchDataResult: MutableStateFlow<List<Pokemon>?> = MutableStateFlow(null)
    val fetchDataResult: StateFlow<List<Pokemon>?> = _fetchDataResult

    init {
        viewModelScope.launch {
            pokemonUseCase.fetchData().collect {
                it.getOrNull()?.let { pokemon ->
                    Log.d("TAG", "PokemonListActivityViewModel: $pokemon")
                    _fetchDataResult.value = pokemon
                }
            }
        }
    }




}