package com.example.app.domain.repository

import com.example.app.domain.model.FoodItem
import com.example.app.domain.model.Resource

interface IFoodRepository {
    suspend fun getMenu(): Resource<List<FoodItem>>
    suspend fun getFoodDetails(foodId: String): Resource<FoodItem>
    suspend fun searchFood(query: String): Resource<List<FoodItem>>
}