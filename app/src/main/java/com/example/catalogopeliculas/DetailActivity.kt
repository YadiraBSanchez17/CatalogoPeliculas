package com.example.catalogopeliculas

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movieId = intent.getStringExtra("MOVIE_ID")

        if (movieId != null) {
            cargarDetalles(movieId)
        } else {
            Toast.makeText(this, "Error: No se encontró la película", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun cargarDetalles(id: String) {

        RetrofitClient.apiService.getMovieById(id).enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful && response.body() != null) {
                    val pelicula = response.body()!!
                    mostrarDatos(pelicula)
                } else {
                    Toast.makeText(this@DetailActivity, "Error al cargar detalles", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("API_ERROR", "Error de red: ${t.message}")
                Toast.makeText(this@DetailActivity, "Error de conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun mostrarDatos(pelicula: Movie) {

        val imgPoster = findViewById<ImageView>(R.id.imgPosterDetail)
        val txtTitulo = findViewById<TextView>(R.id.txtTituloDetail)
        val txtAnio = findViewById<TextView>(R.id.txtAnioDetail)
        val txtDirector = findViewById<TextView>(R.id.txtDirectorDetail)
        val txtGenero = findViewById<TextView>(R.id.txtGeneroDetail)
        val txtSinopsis = findViewById<TextView>(R.id.txtSinopsisDetail)

        txtTitulo.text = pelicula.titulo
        txtAnio.text = "Año: ${pelicula.anio}"
        txtDirector.text = "Director: ${pelicula.director}"
        txtGenero.text = "Género: ${pelicula.genero}"
        txtSinopsis.text = pelicula.sinopsis

        Glide.with(this)
            .load(pelicula.imagen)
            .into(imgPoster)
    }
}