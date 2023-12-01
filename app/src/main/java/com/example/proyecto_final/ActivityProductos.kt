package com.example.proyecto_final

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class ActivityProductos : AppCompatActivity() {

    // URL de la base de datos de Firebase
    private lateinit var firebaseDatabaseUrl: String

    // Referencia a la base de datos de Firebase
    private lateinit var databaseReference: DatabaseReference

    // RecyclerView para mostrar la lista de productos
    private lateinit var recyclerView: RecyclerView

    // Adaptador para la lista de productos
    private lateinit var productosAdapter: ProductosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        // Obtener la URL de la base de datos de Firebase desde el intent
        firebaseDatabaseUrl = intent.getStringExtra("firebaseDatabaseUrl") ?: ""

        // Inicializar la referencia a la base de datos
        databaseReference = FirebaseDatabase.getInstance(firebaseDatabaseUrl).reference

        // Inicializar el RecyclerView y el adaptador
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productosAdapter = ProductosAdapter { producto -> abrirPaginaWeb(producto.url) }
        recyclerView.adapter = productosAdapter

        // Obtener la categoría de productos del intent
        val categoria = intent.getStringExtra("categoria") ?: ""

        // Cargar y mostrar los productos de la categoría
        cargarProductos(categoria)
    }

    // Método para cargar y mostrar los productos de la categoría
    private fun cargarProductos(categoria: String) {
        val productosList = mutableListOf<Producto>()

        // Escuchar cambios en la base de datos para la categoría específica
        databaseReference.child(categoria).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Iterar a través de los productos y agregarlos a la lista
                for (productoSnapshot in snapshot.children) {
                    val nombre = productoSnapshot.child("nombre").getValue(String::class.java) ?: ""
                    val url = productoSnapshot.child("url").getValue(String::class.java) ?: ""

                    val producto = Producto(nombre, url)
                    productosList.add(producto)
                }

                // Actualizar la lista en el adaptador
                productosAdapter.submitList(productosList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar el error en caso de que la lectura de la base de datos falle

            }
        })
    }

    // Método para abrir la página web del producto
    private fun abrirPaginaWeb(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
