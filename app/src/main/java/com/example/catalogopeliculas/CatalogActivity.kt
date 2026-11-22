package com.example.catalogopeliculas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatalogActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        recyclerView = findViewById(R.id.recyclerViewPeliculas)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        adapter = MovieAdapter(emptyList()) { peliculaSeleccionada ->

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("MOVIE_ID", peliculaSeleccionada.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        cargarPeliculas()
    }

    override fun onResume() {
        super.onResume()
        cargarPeliculas()
    }

    private fun cargarPeliculas() {

        RetrofitClient.apiService.getMovies().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                if (response.isSuccessful && response.body() != null) {
                    val listaDePeliculas = response.body()!!

                    adapter.updateData(listaDePeliculas)
                } else {
                    Toast.makeText(this@CatalogActivity, "No se encontraron películas", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {

                Log.e("API_ERROR", "Error de red: ${t.message}")
                Toast.makeText(this@CatalogActivity, "Error al conectar con la base de datos", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_catalog, menu)
        return true
    }


    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.opcion_admin -> {

                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}