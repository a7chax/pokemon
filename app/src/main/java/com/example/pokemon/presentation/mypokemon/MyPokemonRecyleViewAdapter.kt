package com.example.pokemon.presentation.mypokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.domain.entities.*

interface OnItemClickListener {
    fun onClickEdit(pokemon: MyPokemon)
    fun onClickRelease(pokemon: MyPokemon)
}


class MyPokemonRecyleViewAdapter(
    private val dataSet : List<MyPokemon>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MyPokemonRecyleViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val btnRelease: Button
        val btnEdit: TextView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.textView)
            btnRelease = view.findViewById(R.id.button2)
            btnEdit = view.findViewById(R.id.button1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_pokemon_item, parent, false)


        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.btnEdit.setOnClickListener {
            onItemClickListener.onClickEdit(dataSet[position])
        }

        holder.textView.text = "${dataSet[position].name}  (${dataSet[position].pokemonName})"

        holder.btnRelease.setOnClickListener {
            onItemClickListener.onClickRelease(dataSet[position])
        }


    }


    override fun getItemCount() = dataSet.size

}