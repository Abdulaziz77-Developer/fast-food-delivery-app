package com.example.app.dataLayer.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class OrderRequestDto(
    @SerialName("user_id") val userId: String,
    @SerialName("items") val items: List<OrderItemDto>,
    @SerialName("delivery_address") val address: String,
    @SerialName("total_amount") val totalAmount: Int
)

@Serializable
data class OrderItemDto(
    @SerialName("food_id") val foodId: String,
    @SerialName("quantity") val quantity: Int
)