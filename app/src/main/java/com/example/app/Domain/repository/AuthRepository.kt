package com.example.app.Domain.repository

import com.example.app.Domain.models.AuthResult

interface AuthRepository {
    suspend fun registerUser(fullName: String, email: String, password: String): Result<AuthResult>
}