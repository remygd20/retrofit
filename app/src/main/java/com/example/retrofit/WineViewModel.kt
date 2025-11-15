package com.example.retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WineViewModel : ViewModel() {

    private val _wines = MutableLiveData<List<Wines>>()
    val wines: LiveData<List<Wines>> = _wines

    // LiveData para mensajes de error (si algo falla)
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        loadWines()
    }

    private fun loadWines() {
        viewModelScope.launch {
            try {
                val response = ClientRetro.instance.getRedWines()

                if (response.isSuccessful) {
                    _wines.value = response.body()
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Error de red: ${e.message}"
            }
        }
    }
}