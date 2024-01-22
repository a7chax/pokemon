package com.example.pokemon.presentation.pokemonlist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.data.*
import com.example.pokemon.data.localData.*
import com.example.pokemon.domain.entities.Pokemon
import com.example.pokemon.presentation.detailpokemon.DetailPokemonActivity
import com.example.pokemon.presentation.mypokemon.MyPokemonActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

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

//        val database = PokemonDatabase.getInstance(applicationContext)
//        val pokemonDao = database.pokemonDao()
//
//        GlobalScope.launch {
//            val pikachu = PokemonRequestLocal(
//                name = "Ash",
//                pokemonName = "Pikachu",
//                images = "https://example.com/pikachu.jpg",
//            )
//            pokemonDao.insert(pikachu)
//
//            // Query all Pokemons
//            val allPokemons = pokemonDao.getAllPokemons()
//
//            Log.d("PokemonListActivity", "All Pokemons: $allPokemons")
//            // Handle the result as needed
//        }

        setupRecyclerView()
        setupView()
    }

    fun setupRecyclerView() {
        lifecycleScope.launch {
            viewModel.fetchDataResult.collect {
                val adapter = PokemonListRecycleViewAdapter(it ?: emptyList(), this@PokemonListActivity)
                val recyclerView : RecyclerView= findViewById<RecyclerView>(R.id.rv_pokemon)
                recyclerView.adapter = adapter
            }
        }

    }

    fun setupView(){
        val tvMyPokemon : TextView = findViewById<TextView>(R.id.tv_my_pokemon)
        tvMyPokemon.setOnClickListener {
            startActivity(MyPokemonActivity.getIntent(this))
        }
    }

    override fun onItemClick(pokemon: Pokemon) {
       startActivity(DetailPokemonActivity.getIntent(this, pokemon))
    }
}