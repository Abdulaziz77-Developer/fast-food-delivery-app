package com.example.app.dataLayer.repository

import com.example.app.dataLayer.remote.FoodApiService
import com.example.app.dataLayer.mapper.toDomain
import com.example.app.domain.model.Order
import com.example.app.domain.model.Resource
import com.example.app.domain.repository.IEmployeeRepository

class EmployeeRepositoryImpl(
    private val api: FoodApiService
) : IEmployeeRepository {

    override suspend fun getAssignedOrders(employeeId: String): Resource<List<Order>> {
        return try {
            // Вызываем метод из API для получения заказов конкретного сотрудника
            val response = api.getEmployeeOrders(employeeId)

            // Превращаем список OrderResponseDto в список Domain-моделей Order
            Resource.Success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Resource.Error("Ошибка при получении заказов: ${e.localizedMessage ?: "Неизвестная ошибка"}")
        }
    }

    override suspend fun updateOrderStatus(orderId: String, newStatus: String): Resource<Boolean> {
        return try {
            // Отправляем запрос на обновление статуса (например, "В пути" или "Доставлен")
            // В API это обычно PUT или PATCH запрос
            val response = api.updateOrderStatus(orderId, newStatus)

            if (response.isSuccessful) {
                Resource.Success(true)
            } else {
                Resource.Error("Не удалось обновить статус: код ${response.code()}")
            }
        } catch (e: Exception) {
            Resource.Error("Ошибка сети при обновлении статуса: ${e.message}")
        }
    }
}