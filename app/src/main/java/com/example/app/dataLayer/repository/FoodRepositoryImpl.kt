package com.example.app.dataLayer.repository

import com.example.app.dataLayer.mapper.toDomain
import com.example.app.dataLayer.remote.FoodApiService
import com.example.app.domain.model.FoodItem
import com.example.app.domain.model.Resource
import com.example.app.domain.repository.IFoodRepository

class FoodRepositoryImpl(
    private val api: FoodApiService
) : IFoodRepository {

    override suspend fun getMenu(): Resource<List<FoodItem>> {
        return try {
            val response = api.getAllFood()
            // Маппим список DTO из сети в список моделей для UI
            Resource.Success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Resource.Error("Не удалось загрузить меню: ${e.localizedMessage ?: "Неизвестная ошибка"}")
        }
    }

    override suspend fun getFoodDetails(foodId: String): Resource<FoodItem> {
        return try {
            val response = api.getFoodById(foodId)
            Resource.Success(response.toDomain())
        } catch (e: Exception) {
            Resource.Error("Ошибка загрузки блюда: ${e.localizedMessage}")
        }
    }

    override suspend fun searchFood(query: String): Resource<List<FoodItem>> {
        return try {
            // Предполагаем, что в FoodApiService есть метод поиска или фильтруем на лету
            val allFood = api.getAllFood()
            val filteredList = allFood
                .filter { it.name.contains(query, ignoreCase = true) }
                .map { it.toDomain() }

            Resource.Success(filteredList)
        } catch (e: Exception) {
            Resource.Error("Ошибка при поиске: ${e.localizedMessage}")
        }
    }
}