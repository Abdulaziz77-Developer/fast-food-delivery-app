package com.example.app.Data.remote

import com.example.app.Data.models.RegisterRequestDto
import com.example.app.Data.models.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/Auth/register")
    suspend fun register(
        @Body request: RegisterRequestDto
    ): Response<RegisterResponseDto>
}