package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R // Importación de la clase R

// El constructor recibe la lista de vinos.
class WineAdapter(private var wines: List<Wines>) :
    RecyclerView.Adapter<WineAdapter.WineViewHolder>() {

    // 1. ViewHolder: Contiene la referencia a los elementos de la vista de cada fila.
    class WineViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Asume que estos son los IDs de los TextViews en el layout item_wine.xml
        val wineryTextView: TextView = view.findViewById(R.id.wineryTextView)
        val wineTextView: TextView = view.findViewById(R.id.wineTextView)
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
    }

    // 2. onCreateViewHolder: Infla el layout XML (item_wine.xml) para cada fila.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WineViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_wine, parent, false) // <-- Usa el layout XML
        return WineViewHolder(view)
    }

    // 3. onBindViewHolder: Enlaza los datos de un objeto Wine con las vistas de la fila.
    override fun onBindViewHolder(holder: WineViewHolder, position: Int) {
        val wine = wines[position]
        holder.wineryTextView.text = wine.winery
        holder.wineTextView.text = wine.wine
        holder.locationTextView.text = wine.location
        // Puedes añadir aquí más campos (rating, image)
    }

    // 4. getItemCount: Retorna el número total de elementos en la lista.
    override fun getItemCount() = wines.size

    // Función que necesita tu MainActivity para actualizar la lista
    fun updateWines(newWines: List<Wines>) {
        wines = newWines
        notifyDataSetChanged() // Notifica al RecyclerView que los datos cambiaron
    }
}