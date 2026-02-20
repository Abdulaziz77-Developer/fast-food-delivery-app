package com.example.app.domain.model

data class CartItem(
    val foodItem: FoodItem,
    var quantity: Int
) {
    val totalPrice: Int get() = foodItem.price * quantity
}