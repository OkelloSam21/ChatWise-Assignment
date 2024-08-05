package com.samuelokello.chatwiseassignment

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}

data class ProductResponse(
    val products: List<Product>
)