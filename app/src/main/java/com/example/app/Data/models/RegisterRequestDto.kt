package com.example.app.Data.models
import com.google.gson.annotations.SerializedName

data class RegisterRequestDto(
    @SerializedName("fullName") val fullName: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)