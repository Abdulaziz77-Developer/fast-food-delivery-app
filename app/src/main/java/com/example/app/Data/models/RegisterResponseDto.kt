package com.example.app.Data.models

import com.google.gson.annotations.SerializedName

data class RegisterResponseDto(
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: AuthDataDto
)

data class AuthDataDto(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("tokenType") val tokenType: String,
    @SerializedName("expiresIn") val expiresIn: Int,
    @SerializedName("user") val user: UserDto
)

data class UserDto(
    @SerializedName("id") val id: String,
    @SerializedName("fullName") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: Int
)