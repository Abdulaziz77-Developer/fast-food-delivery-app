package com.example.app.domain.repository

import com.example.app.domain.model.Supplier
import com.example.app.domain.model.Resource
import com.example.app.domain.model.FoodItem

interface ISupplierRepository {
    suspend fun getAllSuppliers(): Resource<List<Supplier>>

    suspend fun getSupplierById(id: String): Resource<Supplier>

    suspend fun getFoodBySupplier(supplierId: String): Resource<List<FoodItem>>
}