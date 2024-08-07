package com.samuelokello.chatwiseassignment.data.remote

import com.samuelokello.chatwiseassignment.model.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}

data class ProductResponse(
    val products: List<Product>
)