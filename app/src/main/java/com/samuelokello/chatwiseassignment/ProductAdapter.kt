package com.samuelokello.chatwiseassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private var products: List<Any>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_PRODUCT = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                HeaderViewHolder(view)
            }

            TYPE_PRODUCT -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_product, parent, false)
                ProductViewHolder(view, onItemClick)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind()
            is ProductViewHolder -> holder.bind(products[position] as Product)
        }
    }

    override fun getItemCount(): Int = products.size

    override fun getItemViewType(position: Int): Int {
        return when (products[position]) {
            is String -> TYPE_HEADER
            is Product -> TYPE_PRODUCT
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    //    fun updateProducts(newProducts: List<Product>) {
//        products = listOf("Products List") + newProducts
//        notifyDataSetChanged()
//    }
    fun updateProducts(newProducts: List<Product>) {
        val oldSize = products.size
        products = listOf("Products List") + newProducts
        val newSize = products.size

        if (oldSize < newSize) {
            notifyItemRangeInserted(oldSize, newSize - oldSize)
        } else if (oldSize > newSize) {
            notifyItemRangeRemoved(newSize, oldSize - newSize)
        } else {
            notifyItemRangeChanged(1, newSize - 1)
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvHeader: TextView = view.findViewById(R.id.tvHeader)

        fun bind() {
            "Products List".also { tvHeader.text = it }
        }
    }

    class ProductViewHolder(
        view: View,
        private val onItemClick: (Product) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        private val tvPrice: TextView = view.findViewById(R.id.tvPrice)

        fun bind(product: Product) {
            tvTitle.text = product.title
            "Price: $${product.price}".also { tvPrice.text = it }
            itemView.setOnClickListener { onItemClick(product) }
        }
    }
}