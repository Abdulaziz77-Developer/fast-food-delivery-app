package com.example.app.dataLayer.repository

import com.example.app.dataLayer.remote.FoodApiService
import com.example.app.dataLayer.mapper.toDomain
import com.example.app.domain.model.FoodItem
import com.example.app.domain.model.Resource
import com.example.app.domain.model.Supplier
import com.example.app.domain.repository.ISupplierRepository

class SupplierRepositoryImpl(
    private val api: FoodApiService
) : ISupplierRepository {

    override suspend fun getAllSuppliers(): Resource<List<Supplier>> {
        return try {
            val response = api.getAllSuppliers()
            // Конвертируем список DTO от .NET в список Domain-моделей Supplier
            Resource.Success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Resource.Error("Ошибка загрузки списка ресторанов: ${e.localizedMessage}")
        }
    }

    override suspend fun getSupplierById(id: String): Resource<Supplier> {
        return try {
            // Если в API нет отдельного метода getById, можно получить всех и отфильтровать
            // Но лучше, если в FoodApiService есть метод getSupplierById
            val response = api.getAllSuppliers().find { it.id == id }
            if (response != null) {
                Resource.Success(response.toDomain())
            } else {
                Resource.Error("Ресторан не найден")
            }
        } catch (e: Exception) {
            Resource.Error("Ошибка при получении данных ресторана")
        }
    }

    override suspend fun getFoodBySupplier(supplierId: String): Resource<List<FoodItem>> {
        return try {
            // Получаем всю еду и оставляем только ту, что принадлежит этому поставщику
            // Это типичный сценарий для связи .NET -> Android
            val allFood = api.getAllFood()
            val filteredFood = allFood
                .filter { it.supplierId == supplierId }
                .map { it.toDomain() }

            Resource.Success(filteredFood)
        } catch (e: Exception) {
            Resource.Error("Ошибка загрузки меню ресторана: ${e.message}")
        }
    }
}