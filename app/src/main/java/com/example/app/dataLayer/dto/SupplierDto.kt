package com.example.app.dataLayer.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SupplierDto(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("rating") val rating: Double,
    @SerialName("logo_url") val logoUrl: String
)