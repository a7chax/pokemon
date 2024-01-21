package com.example.pokemon.presentation.pokemonlist

import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.domain.entities.Pokemon
import kotlinx.coroutines.NonDisposableHandle.parent


interface OnItemClickListener {
    fun onItemClick(pokemon: Pokemon)
}

class CustomAdapter(
    private val dataSet: List<Pokemon>,
    private val onItemClickListener: OnItemClickListener
    ) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */



    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView
        val cardView: CardView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.textView)
            cardView = view.findViewById(R.id.cardView)
        }


    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)


        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.cardView.setOnClickListener {
            onItemClickListener.onItemClick(dataSet[position])
        }
        viewHolder.textView.text = dataSet[position].name
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
