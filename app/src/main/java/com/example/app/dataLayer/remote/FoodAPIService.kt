package com.example.app.dataLayer.remote

import com.example.app.data.dto.LoginRequestDto
import com.example.app.dataLayer.dto.FoodDto
import com.example.app.dataLayer.dto.SupplierDto
import com.example.app.dataLayer.dto.LoginResponseDto
import com.example.app.dataLayer.dto.OrderResponseDto
import com.example.app.dataLayer.dto.OrderRequestDto

import retrofit2.Response
import retrofit2.http.*

interface FoodApiService {

    @GET("food")
    suspend fun getAllFood(): List<FoodDto>

    @GET("food/{id}")
    suspend fun getFoodById(@Path("id") id: String): FoodDto

    // 2. Поставщики/Рестораны
    @GET("suppliers")
    suspend fun getAllSuppliers(): List<SupplierDto>

    // 3. Заказы
    @POST("orders")
    suspend fun placeOrder(@Body orderRequest: OrderRequestDto): Response<Unit>

    @GET("orders/user/{userId}")
    suspend fun getOrderHistory(@Path("userId") userId: String): List<OrderResponseDto>

    // 4. Авторизация
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequestDto): LoginResponseDto

    // В файле FoodApiService.kt
    @GET("employee/orders/{employeeId}")
    suspend fun getEmployeeOrders(@Path("employeeId") employeeId: String): List<OrderResponseDto>

    @PATCH("orders/{orderId}/status")
    suspend fun updateOrderStatus(
        @Path("orderId") orderId: String,
        @Query("status") newStatus: String
    ): retrofit2.Response<Unit>
}