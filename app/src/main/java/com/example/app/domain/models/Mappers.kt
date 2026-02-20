package com.example.app.dataLayer.mapper

import com.example.app.dataLayer.dto.*
import com.example.app.domain.model.*

// 1. Превращаем еду из DTO в понятную для приложения модель
fun FoodDto.toDomain(): FoodItem {
    return FoodItem(
        id = id,
        supplierId = supplierId,
        name = name,
        restaurantName = "Restaurant", // Можно будет доработать, когда придет инфа о поставщике
        price = price,
        imageUrl = imageUrl,
        description = description
    )
}

// 2. Превращаем ресторан из DTO в Domain
fun SupplierDto.toDomain(): Supplier {
    return Supplier(
        id = id,
        name = name,
        imageUrl = logoUrl,
        rating = rating,
        deliveryTime = "20-30 min", // Если бэкенд не прислал, ставим дефолт
        distance = "1.5 km"
    )
}

// 3. Превращаем ответ по истории заказов в понятный список
fun OrderResponseDto.toDomain(): Order {
    return Order(
        id = id,
        items = emptyList(), // Детали заказа обычно подгружаются отдельным запросом
        totalPrice = totalPrice,
        date = date,
        status = when (status.lowercase()) {
            "pending" -> OrderStatus.PENDING
            "delivered" -> OrderStatus.DELIVERED
            "cancelled" -> OrderStatus.CANCELLED
            else -> OrderStatus.PROCESS
        },
        deliveryAddress = ""
    )
}

// 4. ОБРАТНЫЙ МАППЕР: Из корзины в формат для отправки на .NET
fun List<CartItem>.toRequestDto(userId: String, address: String): OrderRequestDto {
    return OrderRequestDto(
        userId = userId,
        address = address,
        totalAmount = this.sumOf { it.totalPrice },
        items = this.map { item ->
            OrderItemDto(
                foodId = item.foodItem.id,
                quantity = item.quantity
            )
        }
    )
}