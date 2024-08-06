package com.samuelokello.chatwiseassignment.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.samuelokello.chatwiseassignment.Product
import com.samuelokello.chatwiseassignment.R

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productJson = intent.getStringExtra("product")

        val product = Gson().fromJson(productJson, Product::class.java)
        product?.let {
            findViewById<TextView>(R.id.tvTitle).text = it.title
            findViewById<TextView>(R.id.tvDescription).text = it.description
            "Price: $${it.price}".also { findViewById<TextView>(R.id.tvPrice).text = it }
        }
    }
}