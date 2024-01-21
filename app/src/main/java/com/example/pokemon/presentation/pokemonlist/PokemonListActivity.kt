package com.example.pokemon.presentation.pokemonlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListActivity :AppCompatActivity() {

    val viewModel: PokemonListActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    fun setupRecyclerView() {

        lifecycleScope.launch {
            viewModel.fetchDataResult.collect {
                val adapter = CustomAdapter(it?.results ?: emptyList())
                val recyclerView : RecyclerView= findViewById<RecyclerView>(R.id.rv_pokemon)
                recyclerView.adapter = adapter
            }
        }


    }
}