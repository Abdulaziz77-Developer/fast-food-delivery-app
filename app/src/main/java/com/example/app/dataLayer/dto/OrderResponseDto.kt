package com.example.app.dataLayer.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class OrderResponseDto(
    @SerialName("id") val id: String,
    @SerialName("order_date") val date: String,
    @SerialName("status") val status: String, // Напр. "Pending", "Delivered"
    @SerialName("total_price") val totalPrice: Int
)