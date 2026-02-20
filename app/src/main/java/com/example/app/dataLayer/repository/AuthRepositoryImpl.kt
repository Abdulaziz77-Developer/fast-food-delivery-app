package com.example.app.data.repository

import com.example.app.dataLayer.remote.FoodApiService
import com.example.app.data.dto.LoginRequestDto
import com.example.app.domain.model.Resource
import com.example.app.domain.model.UserProfile
import com.example.app.domain.repository.IAuthRepository

class AuthRepositoryImpl(
    private val api: FoodApiService
) : IAuthRepository {

    override suspend fun login(email: String, password: String): Resource<String> {
        return try {
            val response = api.login(LoginRequestDto(email, password))
            // Возвращаем токен (в будущем его нужно сохранить в SharedPreferences)
            Resource.Success(response.token)
        } catch (e: Exception) {
            Resource.Error("Ошибка входа: ${e.localizedMessage}")
        }
    }

    override suspend fun getUserProfile(userId: String): Resource<UserProfile> {
        return try {
            // Здесь мы могли бы вызвать api.getUser(userId)
            Resource.Loading()
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Ошибка профиля")
        }
    }

    override suspend fun updateProfile(profile: UserProfile): Resource<Boolean> {
        return Resource.Loading()
    }
}