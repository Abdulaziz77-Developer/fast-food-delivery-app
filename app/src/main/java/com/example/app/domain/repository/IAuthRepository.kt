package com.example.app.domain.repository

import com.example.app.domain.model.UserProfile
import com.example.app.domain.model.Resource

interface IAuthRepository {
    suspend fun login(email: String, password: String): Resource<String> // Возвращает токен
    suspend fun getUserProfile(userId: String): Resource<UserProfile>
    suspend fun updateProfile(profile: UserProfile): Resource<Boolean>
}