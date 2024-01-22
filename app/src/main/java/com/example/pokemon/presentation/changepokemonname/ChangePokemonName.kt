package com.example.pokemon.presentation.changepokemonname

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemon.R
import com.example.pokemon.domain.entities.MyPokemon
import com.example.pokemon.presentation.mypokemon.MyPokemonActivity
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangePokemonName :AppCompatActivity() {

    val viewModel: ChangePokemonNameViewModel by viewModels()

    companion object {
        fun getIntent(activity: AppCompatActivity, myPokemon: MyPokemon) : Intent {
            val intent = Intent(activity, ChangePokemonName::class.java)
            intent.putExtra("myPokemon", myPokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_pokemon_name)

        val getMyPokemon = intent.extras?.getParcelable<MyPokemon>("myPokemon")
        setupView(getMyPokemon ?: MyPokemon("", "", "", ""))
        setupToolbar()
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
        val textInputLayout : TextInputEditText = findViewById(R.id.etChangePokemonName)
        val button : Button = findViewById(R.id.btnChangePokemonName)

        button.setOnClickListener {
            if(textInputLayout.text.toString().isNotEmpty()){
                viewModel.updatePokemonName(pokemon.id, textInputLayout.text.toString())
                lifecycleScope.launch {
                    viewModel._updatePokemonNameStatus.collect {
                        if(it){
                            Toast.makeText(this@ChangePokemonName, "Pokemon name changed", Toast.LENGTH_SHORT).show()
                            startActivity(MyPokemonActivity.getIntent(this@ChangePokemonName))
                        }
                    }
                }
            }

        }

        textInputLayout.setText(pokemon.name)

    }





}