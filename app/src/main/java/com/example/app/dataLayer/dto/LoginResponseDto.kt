package com.example.app.dataLayer.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LoginResponseDto(
    @SerialName("token") val token: String,
    @SerialName("user_id") val userId: String,
    @SerialName("user_name") val userName: String
)