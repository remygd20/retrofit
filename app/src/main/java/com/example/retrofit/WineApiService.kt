package com.example.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface WineApiService {

    /**
     * Define la llamada GET para obtener la lista de vinos tintos.
     */
    @GET("wines/reds")
    suspend fun getRedWines(): Response<List<Wines>>
}