package com.example.catalogopeliculas

data class Movie(
    val id: String? = null,
    val titulo: String,
    val director: String,
    val anio: String,
    val genero: String,
    val sinopsis: String,
    val imagen: String
)
