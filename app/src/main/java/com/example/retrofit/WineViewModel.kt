package com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WineViewModel : ViewModel() {

    // LiveData para la lista de vinos (datos a mostrar)
    private val _wines = MutableLiveData<List<Wines>>()
    val wines: LiveData<List<Wines>> = _wines

    // LiveData para mensajes de error (si algo falla)
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        // La llamada a la API inicia tan pronto se crea el ViewModel
        loadWines()
    }

    private fun loadWines() {
        // Ejecuta la llamada dentro del scope del ViewModel
        viewModelScope.launch {
            try {
                // Llama al servicio API (la función suspendida)
                val response = ClientRetro.instance.getRedWines()

                if (response.isSuccessful) {
                    _wines.value = response.body() // Envía los datos a la Activity
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                // Maneja fallos de conexión o excepciones
                _error.value = "Error de red: ${e.message}"
            }
        }
    }
}