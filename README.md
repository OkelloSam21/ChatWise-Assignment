﻿# Chatwise Assignment

This project is an Android application that displays a list of products and their details. It uses Retrofit for network requests, Gson for JSON serialization/deserialization, and Coil for image loading.

## Features

- Fetches a list of products from a remote API.
- Displays the list of products in a `RecyclerView`.
- Shows product details in a separate activity when a product is clicked.

## Technologies Used

- Kotlin
- Retrofit
- Gson
- Coil
- AndroidX Libraries
- Coroutines

## Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/samuelokello/chatwiseassignment/
│   │   │   ├── data/
│   │   │   │   └── remote/
│   │   │   │       └── ApiService.kt
│   │   │   ├── domain/
│   │   │   │   └── ProductRepository.kt
│   │   │   ├── ui/
│   │   │   │   ├── ProductAdapter.kt
│   │   │   │   ├── ProductDetailActivity.kt
│   │   │   │   └── ProductListActivity.kt
│   │   │   └── Product.kt
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_product_detail.xml
│   │   │   │   ├── activity_product_list.xml
│   │   │   │   └── product_item.xml
│   │   │   └── values/
│   │   │       └── strings.xml
│   └── build.gradle
└── build.gradle
```

## Getting Started

### Prerequisites

- Android Studio
- Kotlin 1.5+
- Gradle 7.0+

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/OkelloSam21/chatwise-assignment.git
    ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.

### Running the App

1. Connect an Android device or start an emulator.
2. Click on the "Run" button in Android Studio.

## Code Overview

### `ApiService`

Defines the API endpoints using Retrofit.

```kotlin
interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse
}
```

### `ProductRepository`

Handles data operations and provides a clean API for data access to the rest of the application.

```kotlin
class ProductRepository {
    private val apiService: ApiService = // initialize Retrofit service

    suspend fun getProducts(): List<Product> {
        return apiService.getProducts().products
    }
}
```

### `ProductListActivity`

Displays a list of products using a `RecyclerView`.

```kotlin
class ProductListActivity : AppCompatActivity() {
    // Implementation details
}
```

### `ProductDetailActivity`

Displays the details of a selected product.

```kotlin
class ProductDetailActivity : AppCompatActivity() {
    // Implementation details
}
```

### `ProductAdapter`

Adapter for the `RecyclerView` in `ProductListActivity`.

```kotlin
class ProductAdapter(
    private var products: List<Product>,
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    // Implementation details
}
```

## Screenshots
![image](https://github.com/user-attachments/assets/601fa3d9-ade9-4a5b-8eda-85d0338bcac5)
![image](https://github.com/user-attachments/assets/fac4b6b9-3b66-4825-b7f0-c567bff239a6)
![image](https://github.com/user-attachments/assets/75d97d16-9ead-4656-bb6e-1fddf06635e0)



This project is licensed under the MIT License. See the `LICENSE` file for details.
