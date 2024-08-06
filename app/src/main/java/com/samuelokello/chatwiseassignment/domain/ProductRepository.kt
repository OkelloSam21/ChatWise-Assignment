package com.samuelokello.chatwiseassignment.domain

import com.samuelokello.chatwiseassignment.data.remote.ApiService
import com.samuelokello.chatwiseassignment.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(ApiService::class.java)

    suspend fun getProducts(): List<Product> {
        return apiService.getProducts().products
    }
}