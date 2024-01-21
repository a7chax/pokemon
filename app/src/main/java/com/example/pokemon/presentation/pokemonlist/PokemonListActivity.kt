package com.example.pokemon.presentation.pokemonlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.domain.entities.Pokemon
import com.example.pokemon.presentation.detailpokemon.DetailPokemonActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PokemonListActivity :AppCompatActivity(), OnItemClickListener {

    companion object {
        fun getIntent(activity: AppCompatActivity) : Intent {
            val intent = Intent(activity, PokemonListActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }

    val viewModel: PokemonListActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    fun setupRecyclerView() {
        lifecycleScope.launch {
            viewModel.fetchDataResult.collect {
                val adapter = CustomAdapter(it ?: emptyList(), this@PokemonListActivity)
                val recyclerView : RecyclerView= findViewById<RecyclerView>(R.id.rv_pokemon)
                recyclerView.adapter = adapter
            }
        }

    }

    override fun onItemClick(pokemon: Pokemon) {
       startActivity(DetailPokemonActivity.getIntent(this, pokemon))
    }
}