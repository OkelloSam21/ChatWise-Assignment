package com.samuelokello.chatwiseassignment.addapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.samuelokello.chatwiseassignment.Product
import com.samuelokello.chatwiseassignment.R

class ProductAdapter(
    private var products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.title
        holder.description.text = product.description
        "Price: $${product.price}".also { holder.price.text = it }
        holder.imageProgressBar.visibility = View.VISIBLE
        holder.image.load(product.images.first()) {
            listener(
                onSuccess = { _, _ -> holder.imageProgressBar.visibility = View.GONE },
                onError = { _, _ -> holder.imageProgressBar.visibility = View.GONE }
            )
        }
        holder.itemView.setOnClickListener { onItemClick(product) }
    }

    override fun getItemCount(): Int = products.size

    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        notifyDataSetChanged()
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.productTitle)
        val description: TextView = itemView.findViewById(R.id.productDescription)
        val price: TextView = itemView.findViewById(R.id.productPrice)
        val image: ImageView = itemView.findViewById(R.id.productImage)
        val imageProgressBar: ProgressBar = itemView.findViewById(R.id.imageProgressBar)
    }
}