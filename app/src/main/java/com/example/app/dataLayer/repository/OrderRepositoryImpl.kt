package com.example.app.dataLayer.repository

import com.example.app.dataLayer.remote.FoodApiService
import com.example.app.dataLayer.mapper.toDomain
import com.example.app.dataLayer.dto.OrderRequestDto
import com.example.app.dataLayer.dto.OrderItemDto
import com.example.app.domain.model.CartItem
import com.example.app.domain.model.Order
import com.example.app.domain.model.Resource
import com.example.app.domain.repository.IOrderRepository

class OrderRepositoryImpl(
    private val api: FoodApiService
) : IOrderRepository {

    override suspend fun placeOrder(items: List<CartItem>, address: String): Resource<String> {
        return try {
            // 1. Рассчитываем общую сумму заказа, чтобы передать её в DTO
            // Используем sumOf для сложения произведений цены на количество
            val total = items.sumOf { it.foodItem.price * it.quantity }

            // 2. Преобразуем элементы корзины (Domain) в элементы DTO для передачи на сервер
            val orderItemsDto = items.map { item ->
                OrderItemDto(
                    foodId = item.foodItem.id,
                    quantity = item.quantity
                )
            }

            // 3. Формируем объект запроса с учетом всех обязательных полей
            val orderRequest = OrderRequestDto(
                userId = "current_user_id", // В будущем подставим ID из Auth системы
                items = orderItemsDto,
                address = address,
                totalAmount = total // Тот самый параметр, которого не хватало
            )

            // 4. Отправляем запрос на твой .NET API
            val response = api.placeOrder(orderRequest)

            if (response.isSuccessful) {
                Resource.Success("Заказ успешно отправлен на сервер!")
            } else {
                Resource.Error("Ошибка сервера (${response.code()}): Не удалось оформить заказ")
            }
        } catch (e: Exception) {
            // Ловим ошибки сети или парсинга
            Resource.Error("Ошибка соединения: ${e.localizedMessage ?: "Неизвестная ошибка"}")
        }
    }

    override suspend fun getOrderHistory(userId: String): Resource<List<Order>> {
        return try {
            val response = api.getOrderHistory(userId)
            // Превращаем список DTO обратно в список моделей нашего приложения
            Resource.Success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Resource.Error("Не удалось загрузить историю заказов: ${e.message}")
        }
    }
}