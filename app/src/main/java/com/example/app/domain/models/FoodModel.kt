package com.example.app.domain.model

data class FoodItem(
    val id: String,          // GUID от .NET
    val supplierId: String,  // Ссылка на ресторан
    val name: String,
    val restaurantName: String, // Для быстрого отображения в UI
    val price: Int,
    val imageUrl: String,    // Ссылка на картинку с сервера
    val description: String,
    val ingredients: List<String> = emptyList()
)