package com.example.pokemon.presentation.mypokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.pokemon.R
import com.example.pokemon.domain.entities.MyPokemon
import com.example.pokemon.presentation.changepokemonname.ChangePokemonName
import com.example.pokemon.presentation.releasepokemon.ReleasePokemonActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyPokemonActivity :AppCompatActivity(), OnItemClickListener {

    val viewModel : MyPokemonViewModel by viewModels()

    companion object {
        fun getIntent(activity: AppCompatActivity) : Intent {
            val intent = Intent(activity, MyPokemonActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        }

    }

    override fun onClickEdit(pokemon: MyPokemon) {
        startActivity(ChangePokemonName.getIntent(this, pokemon))
    }

    override fun onClickRelease(pokemon: MyPokemon) {
        startActivity(ReleasePokemonActivity.getIntent(this,pokemon))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_pokemon)
        setupAdapter()
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

    fun setupAdapter() {
        lifecycleScope.launch {
            viewModel.myPokemon.collect {
               val adapter =  MyPokemonRecyleViewAdapter(it, this@MyPokemonActivity)
                val recycleView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.rv_my_pokemon)
                recycleView.adapter = adapter
            }
        }
    }
}