package com.example.app.Domain.models
data class AuthResult(
    val fullName: String,
    val email: String,
    val token: String // Основной токен для работы
)