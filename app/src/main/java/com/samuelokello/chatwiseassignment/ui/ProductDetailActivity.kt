package com.samuelokello.chatwiseassignment.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.samuelokello.chatwiseassignment.model.Product
import com.samuelokello.chatwiseassignment.R
import com.samuelokello.chatwiseassignment.addapter.ReviewAdapter

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productJson = intent.getStringExtra("product")
        val backButton: ImageView = findViewById(R.id.back_button_icon)

        backButton.setOnClickListener { finish() }

        val product = Gson().fromJson(productJson, Product::class.java)
        product?.let {
            findViewById<ImageView>(R.id.productImage).load(it.images.first())
            findViewById<TextView>(R.id.tvTitle).text = it.title
            findViewById<TextView>(R.id.tvBrand).text = getString(R.string.brand, it.brand)
            findViewById<TextView>(R.id.tvPrice).text = getString(R.string.price, "%.2f".format(it.price))
            getString(R.string.discount_off, it.discountPercentage).also { findViewById<TextView>(R.id.tvDiscountPercentage).text = it }
            findViewById<RatingBar>(R.id.ratingBar).rating = it.rating.toFloat()
            findViewById<TextView>(R.id.tvDescription).text = it.description
            findViewById<TextView>(R.id.tvCategory).text = getString(R.string.category, it.category)
            findViewById<TextView>(R.id.tvStock).text = getString(R.string.in_stock, it.stock.toString())
            findViewById<TextView>(R.id.tvAvailabilityStatus).text = getString(R.string.status, it.availabilityStatus)
            findViewById<TextView>(R.id.tvSku).text = getString(R.string.sku, it.sku)
            findViewById<TextView>(R.id.tvWeight).text = getString(R.string.weight_g, it.weight.toString())
            findViewById<TextView>(R.id.tvDimensions).text = getString(
                R.string.dimensions_w_x_h_x_d,
                "%.2f".format(it.dimensions.width),
                "%.2f".format(it.dimensions.height),
                "%.2f".format(it.dimensions.depth)
            )
            findViewById<TextView>(R.id.tvWarrantyInformation).text = getString(R.string.warranty, it.warrantyInformation)
            findViewById<TextView>(R.id.tvShippingInformation).text = it.shippingInformation
            findViewById<TextView>(R.id.tvReturnPolicy).text = it.returnPolicy
            findViewById<TextView>(R.id.tvMinimumOrderQuantity).text = getString(R.string.minimum_order, it.minimumOrderQuantity.toString())

            // Set up RecyclerView for reviews
            val reviewsRecyclerView: RecyclerView = findViewById(R.id.rvReviews)
            reviewsRecyclerView.layoutManager = LinearLayoutManager(this)
            reviewsRecyclerView.adapter = ReviewAdapter(it.reviews)

            // Set up FloatingActionButton
            findViewById<FloatingActionButton>(R.id.fabAddToCart).setOnClickListener {
                // TODO: Implement add to cart functionality
            }
        }
    }
}