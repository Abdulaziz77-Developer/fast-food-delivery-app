package com.example.app.domain.repository

import com.example.app.domain.model.CartItem
import com.example.app.domain.model.Order
import com.example.app.domain.model.Resource

interface IOrderRepository {
    suspend fun placeOrder(items: List<CartItem>, address: String): Resource<String>
    suspend fun getOrderHistory(userId: String): Resource<List<Order>>
}