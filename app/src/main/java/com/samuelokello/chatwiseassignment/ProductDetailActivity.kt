package com.samuelokello.chatwiseassignment

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product = intent.getParcelableExtra<Product>("product")
        product?.let {
            findViewById<TextView>(R.id.tvTitle).text = it.title
            findViewById<TextView>(R.id.tvDescription).text = it.description
            findViewById<TextView>(R.id.tvPrice).text = "Price: $${it.price}"
        }
    }
}