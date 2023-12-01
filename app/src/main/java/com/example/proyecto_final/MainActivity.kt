package com.example.proyecto_final

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var firebaseDatabaseUrl: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        firebaseDatabaseUrl = "https://proyectofinal-b4483-default-rtdb.firebaseio.com/"

            findViewById<ImageView>(R.id.cpu).setOnClickListener {
            abrirActivityProductos("CPU")
        }
        findViewById<ImageView>(R.id.cooler).setOnClickListener {
            abrirActivityProductos("Ventilador")
        }
        findViewById<ImageView>(R.id.motherboard).setOnClickListener {
            abrirActivityProductos("Tarjeta madre")
        }
        findViewById<ImageView>(R.id.ram).setOnClickListener {
            abrirActivityProductos("Memoria Ram")
        }
        findViewById<ImageView>(R.id.ssd).setOnClickListener {
            abrirActivityProductos("Memoria SSD")
        }
        findViewById<ImageView>(R.id.videocard).setOnClickListener {
            abrirActivityProductos("Tarjeta grafica")
        }
        findViewById<ImageView>(R.id.FuentePoder).setOnClickListener {
            abrirActivityProductos("Fuente de poder")
        }
        findViewById<ImageView>(R.id.cases).setOnClickListener {
            abrirActivityProductos("Cases")
        }
    }

    private fun abrirActivityProductos(categoria: String) {
        val intent = Intent(this, ActivityProductos::class.java)
        intent.putExtra("Categoria", categoria)
        intent.putExtra("firebaseDatabaseUrl", firebaseDatabaseUrl)
        startActivity(intent)
    }
}
