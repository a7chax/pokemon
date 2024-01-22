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

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


    fun setupView(pokemon: Pokemon, isCatch: Boolean){
        val imgPokemon: ImageView = findViewById(R.id.imgPokemonCatch)
        val tvCongrats : TextView = findViewById<TextView>(R.id.tvCongratulations)
        val tiPokemonName: TextInputEditText = findViewById(R.id.etPokemonName)
        val tilPokemonName: TextInputLayout = findViewById(R.id.tiPokemonName)
        val btnAddPokemon: Button = findViewById(R.id.btnSave)
        val tvPokemonName: TextView = findViewById(R.id.tvPokemonName)

        if (isCatch) {
            tilPokemonName.visibility = View.VISIBLE
            tvCongrats.text = "Congratulations, you have catch ${pokemon.name}."
            tiPokemonName.setText("Mighty ${pokemon.name}")

        } else{
            tvPokemonName.visibility = View.GONE
            tilPokemonName.visibility = View.GONE
            tvCongrats.text = "Sorry, you have failed to catch ${pokemon.name}."
            btnAddPokemon.text = "Back to Pokemon List"
        }



        btnAddPokemon.setOnClickListener {
            val pokemonName = tiPokemonName.text.toString()
            if (pokemonName.isNotEmpty()) {
                val requestBody = PokemonRequestBody(
                    name = pokemonName,
                    pokemonName = pokemon.name,
                    images = pokemon.images
                )

                viewModel.handleaddPokemon(requestBody)
                lifecycleScope.launch {
                    viewModel.addPokemonStatus.collect {
                        startActivity(PokemonListActivity.getIntent(this@CatchPokemonActivity))
                    }
                }
                Toast.makeText(this, "Pokemon added", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(PokemonListActivity.getIntent(this@CatchPokemonActivity))
            }
        }



        Picasso.get()
            .load(pokemon.images)
            .into(imgPokemon)

    }
}