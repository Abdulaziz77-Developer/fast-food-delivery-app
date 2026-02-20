package com.example.app.dataLayer.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class FoodDto(
    @SerialName("id") val id: String, // GUID
    @SerialName("name") val name: String,
    @SerialName("price") val price: Int,
    @SerialName("description") val description: String,
    @SerialName("supplier_id") val supplierId: String,
    @SerialName("image_url") val imageUrl: String
)