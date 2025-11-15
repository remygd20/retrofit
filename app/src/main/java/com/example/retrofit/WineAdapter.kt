package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R // Importación de la clase R

class WineAdapter(private var wines: List<Wines>) :
    RecyclerView.Adapter<WineAdapter.WineViewHolder>() {

    class WineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val wineryTextView: TextView = view.findViewById(R.id.wineryTextView)
        val wineTextView: TextView = view.findViewById(R.id.wineTextView)
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wine, parent, false) // <-- Usa el layout XML
        return WineViewHolder(view)
    }

    override fun onBindViewHolder(holder: WineViewHolder, position: Int) {
        val wine = wines[position]
        holder.wineryTextView.text = wine.winery
        holder.wineTextView.text = wine.wine
        holder.locationTextView.text = wine.location
    }

    override fun getItemCount() = wines.size

    fun updateWines(newWines: List<Wines>) {
        wines = newWines
        notifyDataSetChanged()
    }
}