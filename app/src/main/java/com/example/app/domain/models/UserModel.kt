package com.example.app.domain.model

data class UserProfile(
    val id: String,          // GUID пользователя из БД
    val name: String,
    val email: String,
    val phone: String,
    val address: String,
    val profileImageUrl: String? = null
)