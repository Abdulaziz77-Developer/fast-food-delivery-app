package com.example.app.Data.repository

import com.example.app.Data.mapper.toDomain
import com.example.app.Data.models.RegisterRequestDto
import com.example.app.Data.remote.AuthApiService
import com.example.app.Domain.models.AuthResult
import com.example.app.Domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val apiService: AuthApiService
) : AuthRepository {

    override suspend fun registerUser(fullName: String, email: String, password: String): Result<AuthResult> {
        return try {
            val request = RegisterRequestDto(fullName, email, password)
            val response = apiService.register(request)

            if (response.isSuccessful && response.body() != null) {
                // Используем наш маппер .toDomain()
                Result.success(response.body()!!.toDomain())
            } else {
                Result.failure(Exception("Ошибка сервера: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}