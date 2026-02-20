package com.example.app.dataLayer.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserUpdateDto(
    @SerialName("name") val name: String,
    @SerialName("phone") val phone: String,
    @SerialName("address") val address: String
)