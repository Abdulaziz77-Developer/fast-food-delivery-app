package com.example.app.Data.mapper

import com.example.app.Data.models.RegisterResponseDto
import com.example.app.Domain.models.AuthResult

fun RegisterResponseDto.toDomain(): AuthResult {
    return AuthResult(
        fullName = this.data.user.fullName,
        email = this.data.user.email,
        token = this.data.accessToken
    )
}