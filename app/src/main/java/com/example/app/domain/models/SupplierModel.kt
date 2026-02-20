package com.example.app.domain.model

data class Supplier(
    val id: String,          // GUID
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val deliveryTime: String,
    val distance: String,
    val isPromoted: Boolean = false
)