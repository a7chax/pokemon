package com.example.pokemon.presentation.detailpokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.pokemon.R
import com.example.pokemon.domain.entities.Pokemon
import com.example.pokemon.presentation.catchpokemon.CatchPokemonActivity
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class DetailPokemonActivity :AppCompatActivity() {



    companion object {
        fun getIntent(activity: AppCompatActivity, pokemon: Pokemon) : Intent {
            val intent = Intent(activity, DetailPokemonActivity::class.java)
            intent.putExtra("pokemon", pokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pokemon)

        val pokemon = intent.extras?.getParcelable<Pokemon>("pokemon")
        val getPokemon = pokemon ?: Pokemon("", 0, "", emptyList(), emptyList())
        setupView(getPokemon)
        setupToolbar(getPokemon.name)
    }


    fun setupToolbar(pokemonName : String){
        val Toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = pokemonName
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setupView(pokemon: Pokemon) {
        val imgPokemon: ImageView = findViewById(R.id.imgPokemon)
        val tvName : TextView = findViewById<TextView>(R.id.tvName)
        val tvElement : TextView = findViewById<TextView>(R.id.tvElement)
        val tvMoves : TextView = findViewById<TextView>(R.id.tvMoves)
        val btncatch : Button = findViewById<Button>(R.id.btnCatch)

        btncatch.setOnClickListener {
            startActivity(CatchPokemonActivity.getIntent(this, pokemon))
        }

        tvName.text = pokemon.name
        tvElement.text = pokemon.type.joinToString(", ")
        tvMoves.text = pokemon.moves.joinToString(", ")
        Picasso.get().load(pokemon.images).into(imgPokemon)

    }
}