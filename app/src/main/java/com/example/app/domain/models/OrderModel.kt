package com.example.app.domain.model

enum class OrderStatus {
    PENDING, PROCESS, DELIVERED, CANCELLED
}

data class Order(
    val id: String,          // GUID заказа
    val items: List<CartItem>,
    val totalPrice: Int,
    val date: String,
    val status: OrderStatus,
    val deliveryAddress: String
)