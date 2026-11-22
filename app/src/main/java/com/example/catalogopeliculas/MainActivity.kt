package com.example.catalogopeliculas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        btnIngresar.setOnClickListener {

            Toast.makeText(this, "Ingresando al catálogo...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)
        }

        btnRegistrarse.setOnClickListener {
            Toast.makeText(this, "Simulando registro...", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, CatalogActivity::class.java)
            startActivity(intent)
        }
    }
}