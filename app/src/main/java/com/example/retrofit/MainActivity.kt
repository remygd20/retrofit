package com.example.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels // Importación para usar 'by viewModels()'
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
// Ya no necesitas importar Call, Callback ni Retrofit

class MainActivity : AppCompatActivity() {

    // Inicializa el ViewModel usando la delegación viewModels()
    private val viewModel: WineViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var wineAdapter: WineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        observeViewModel() // <-- Llama a la función que observa el LiveData

        // **ELIMINAMOS la función loadWines() antigua y su llamada dentro de onCreate**
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializa el adaptador
        wineAdapter = WineAdapter(emptyList())
        recyclerView.adapter = wineAdapter
    }

    private fun observeViewModel() {
        // 1. Observa los datos del ViewModel
        viewModel.wines.observe(this) { winesList ->
            wineAdapter.updateWines(winesList)
        }

        // 2. Observa los errores
        viewModel.error.observe(this) { errorMsg ->
            if (errorMsg != null) {
                Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
            }
        }
    }

    // **NO HAY FUNCIÓN loadWines() AQUÍ**
}