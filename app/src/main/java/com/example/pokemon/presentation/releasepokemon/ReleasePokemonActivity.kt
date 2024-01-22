package com.example.pokemon.presentation.releasepokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemon.R
import com.example.pokemon.domain.entities.MyPokemon
import com.example.pokemon.presentation.mypokemon.MyPokemonActivity
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReleasePokemonActivity :AppCompatActivity() {


    val viewModel: ReleasePokemonViewModel by viewModels()

    companion object {
        fun getIntent(activity: AppCompatActivity, myPokemon : MyPokemon ) : Intent {
            val intent = Intent(activity, ReleasePokemonActivity::class.java)
            intent.putExtra("myPokemon", myPokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_release_pokemon)
        setupToolbar()
        val getMyPokemon = intent.extras?.getParcelable<MyPokemon>("myPokemon")
        setupView(getMyPokemon ?: MyPokemon("", "", "", ""))


    }

    fun setupToolbar(){
        val Toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun setupView(pokemon: MyPokemon){
        val image : ImageView = findViewById(R.id.imgPokemonRelease)
        val btnRelease : Button = findViewById(R.id.btnReleasePokemon)

        btnRelease.setOnClickListener {

            viewModel.releasePokemon(pokemon.id)

            lifecycleScope.launch {
                viewModel._releasePokemonStatus.collect {
                    Log.d("ReleasePokemonActivity", "onCreate: $it")
                    if (it) {
                        Toast.makeText(this@ReleasePokemonActivity, "Pokemon Released", Toast.LENGTH_SHORT).show()
                        startActivity(MyPokemonActivity.getIntent(this@ReleasePokemonActivity))
                    }else{
                        Toast.makeText(this@ReleasePokemonActivity, "Failed to release pokemon", Toast.LENGTH_SHORT).show()

                    }
                }
            }

        }
        Picasso.get()
            .load(pokemon.images)
            .into(image)
    }
}