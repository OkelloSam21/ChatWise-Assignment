package com.samuelokello.chatwiseassignment.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.samuelokello.chatwiseassignment.domain.ProductRepository
import com.samuelokello.chatwiseassignment.R
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private val productRepository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter(emptyList()) { product ->
            val productJson = Gson().toJson(product)
            val intent = Intent(this, ProductDetailActivity::class.java).apply {
                putExtra("product", productJson)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        loadProducts()
    }

    private fun loadProducts() {
        lifecycleScope.launch {
            try {
                val products = productRepository.getProducts()
                adapter.updateProducts(products)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}