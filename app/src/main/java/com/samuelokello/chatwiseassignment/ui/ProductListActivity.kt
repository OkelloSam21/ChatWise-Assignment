package com.samuelokello.chatwiseassignment.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.samuelokello.chatwiseassignment.R
import com.samuelokello.chatwiseassignment.addapter.ProductAdapter
import com.samuelokello.chatwiseassignment.domain.ProductRepository
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter
    private lateinit var progressBar: View
    private val productRepository = ProductRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBarIndicator)
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
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        lifecycleScope.launch {
            try {
                val products = productRepository.getProducts()
                adapter.updateProducts(products)
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            } catch (e: Exception) {
                // Handle error
                progressBar.visibility = View.GONE
            }
        }
    }
}