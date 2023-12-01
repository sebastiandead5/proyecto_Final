package com.example.proyecto_final

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductosAdapter(
    private var productos: List<Producto>,
    private val onItemClick: (Producto) -> Unit
) : RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>() {

    fun updateList(newProductos: List<Producto>) {
        productos = newProductos
        notifyDataSetChanged()
    }

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        val verEnWebButton: Button = itemView.findViewById(R.id.verEnWebButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val currentProducto = productos[position]

        holder.nombreTextView.text = currentProducto.nombre
        holder.verEnWebButton.setOnClickListener {
            onItemClick.invoke(currentProducto)
        }
    }

    override fun getItemCount(): Int {
        return productos.size
    }
}