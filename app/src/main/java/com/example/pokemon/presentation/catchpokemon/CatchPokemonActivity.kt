package com.example.pokemon.presentation.catchpokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemon.R
import com.example.pokemon.domain.entities.*
import com.example.pokemon.presentation.pokemonlist.PokemonListActivity
import com.google.android.material.textfield.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatchPokemonActivity :AppCompatActivity() {

    val viewModel: CatchPokemonViewModel by viewModels()

    companion object {
        fun getIntent(activity: AppCompatActivity, pokemon: Pokemon) : Intent {
            val intent = Intent(activity, CatchPokemonActivity::class.java)
            intent.putExtra("pokemon", pokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catch_pokemon)

        val pokemon = intent.extras?.getParcelable<Pokemon>("pokemon")
        val getPokemon = pokemon ?: Pokemon("", 0, "", emptyList(), emptyList())
        setupToolbar(getPokemon.name)

        lifecycleScope.launch {
            viewModel.catchPokemon.collect {
                    if (it != null) {
                        setupView(getPokemon, it)

                    }

            }
        }
    }



    fun setupToolbar(pokemonName : String){
        val Toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Catching ${pokemonName}"
    }


    fun setupView(pokemon: Pokemon, isCatch: Boolean){
        val imgPokemon: ImageView = findViewById(R.id.imgPokemonCatch)
        val tvCongrats : TextView = findViewById<TextView>(R.id.tvCongratulations)
        val tiPokemonName: TextInputEditText = findViewById(R.id.etPokemonName)
        val tilPokemonName: TextInputLayout = findViewById(R.id.tiPokemonName)
        val btnAddPokemon: Button = findViewById(R.id.btnSave)

        if (isCatch) {
            tilPokemonName.visibility = View.VISIBLE
            tvCongrats.text = "Congratulations, you have catch ${pokemon.name}."

        } else{
            tilPokemonName.visibility = View.GONE
            tvCongrats.text = "Sorry, you have failed to catch ${pokemon.name}."
        }


        tiPokemonName.setText("Mighty ${pokemon.name}")

        btnAddPokemon.setOnClickListener {
            val pokemonName = tiPokemonName.text.toString()
            if (pokemonName.isNotEmpty()) {
                val requestBody = PokemonRequestBody(
                    name = pokemonName,
                    pokemonName = pokemon.name,
                    images = pokemon.images
                )
                viewModel.addPokemon(requestBody)
                startActivity(PokemonListActivity.getIntent(this))
                Toast.makeText(this, "Pokemon added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Pokemon name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }



        Picasso.get()
            .load(pokemon.images)
            .into(imgPokemon)

    }
}