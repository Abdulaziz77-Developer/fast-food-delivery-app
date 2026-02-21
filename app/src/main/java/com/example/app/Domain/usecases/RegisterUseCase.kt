package com.example.app.Domain.usecases

import com.example.app.Domain.models.AuthResult
import com.example.app.Domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(fullName: String, email: String, password: String): Result<AuthResult> {
        return repository.registerUser(fullName, email, password)
    }
}