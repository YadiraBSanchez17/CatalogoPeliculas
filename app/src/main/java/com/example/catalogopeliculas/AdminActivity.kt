package com.example.catalogopeliculas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val etTitulo = findViewById<EditText>(R.id.etTitulo)
        val etAnio = findViewById<EditText>(R.id.etAnio)
        val etDirector = findViewById<EditText>(R.id.etDirector)
        val etGenero = findViewById<EditText>(R.id.etGenero)
        val etSinopsis = findViewById<EditText>(R.id.etSinopsis)
        val etImagenUrl = findViewById<EditText>(R.id.etImagenUrl)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)


        val etIdEliminar = findViewById<EditText>(R.id.etIdEliminar)
        val btnEliminar = findViewById<Button>(R.id.btnEliminar)


        btnGuardar.setOnClickListener {

            val nuevaPelicula = Movie(
                titulo = etTitulo.text.toString(),
                anio = etAnio.text.toString(),
                director = etDirector.text.toString(),
                genero = etGenero.text.toString(),
                sinopsis = etSinopsis.text.toString(),
                imagen = etImagenUrl.text.toString()
            )

            RetrofitClient.apiService.createMovie(nuevaPelicula).enqueue(object : Callback<Movie> {
                override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@AdminActivity, "¡Película guardada con éxito!", Toast.LENGTH_SHORT).show()
                        finish() // Regresa a la pantalla anterior
                    }
                }

                override fun onFailure(call: Call<Movie>, t: Throwable) {
                    Toast.makeText(this@AdminActivity, "Error al guardar", Toast.LENGTH_SHORT).show()
                }
            })
        }

        btnEliminar.setOnClickListener {
            val id = etIdEliminar.text.toString()
            if (id.isNotEmpty()) {

                RetrofitClient.apiService.deleteMovie(id).enqueue(object : Callback<Movie> {
                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AdminActivity, "¡Película eliminada!", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@AdminActivity, "No se encontró el ID", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        Toast.makeText(this@AdminActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}