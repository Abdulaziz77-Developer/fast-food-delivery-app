package com.example.app.domain.repository

import com.example.app.domain.model.Order
import com.example.app.domain.model.Resource

interface IEmployeeRepository {
    suspend fun getAssignedOrders(employeeId: String): Resource<List<Order>>
    suspend fun updateOrderStatus(orderId: String, newStatus: String): Resource<Boolean>
}