package com.example.retrofit

/**
 * Clase para mapear la información anidada de la calificación.
 */
data class Rating(
    val average: String,
    val reviews: String
)

/**
 * Clase principal que mapea cada elemento de la lista de vinos.
 */
data class Wines(
    val winery: String,
    val wine: String,
    val rating: Rating,
    val location: String,
    val image: String,
    val id: Int
)