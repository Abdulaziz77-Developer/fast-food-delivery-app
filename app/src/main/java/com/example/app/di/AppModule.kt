package com.example.app.di

import com.example.app.dataLayer.remote.FoodApiService
import com.example.app.dataLayer.repository.FoodRepositoryImpl
import com.example.app.dataLayer.repository.OrderRepositoryImpl
import com.example.app.dataLayer.repository.SupplierRepositoryImpl
import com.example.app.dataLayer.repository.EmployeeRepositoryImpl
import com.example.app.domain.repository.IFoodRepository
import com.example.app.domain.repository.IOrderRepository
import com.example.app.domain.repository.ISupplierRepository
import com.example.app.domain.repository.IEmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // 1. Настройка Retrofit и единственного API сервиса
    @Provides
    @Singleton
    fun provideFoodApiService(): FoodApiService {
        return Retrofit.Builder()
            // Используй 10.0.2.2 для обращения к localhost твоего .NET из эмулятора
            .baseUrl("http://10.0.2.2:5000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodApiService::class.java)
    }

    // 2. Привязка репозитория Еды
    @Provides
    @Singleton
    fun provideFoodRepository(api: FoodApiService): IFoodRepository {
        return FoodRepositoryImpl(api)
    }

    // 3. Привязка репозитория Заказов
    @Provides
    @Singleton
    fun provideOrderRepository(api: FoodApiService): IOrderRepository {
        return OrderRepositoryImpl(api)
    }

    // 4. Привязка репозитория Поставщиков (Ресторанов)
    @Provides
    @Singleton
    fun provideSupplierRepository(api: FoodApiService): ISupplierRepository {
        return SupplierRepositoryImpl(api)
    }

    // 5. Привязка репозитория Сотрудников
    @Provides
    @Singleton
    fun provideEmployeeRepository(api: FoodApiService): IEmployeeRepository {
        return EmployeeRepositoryImpl(api)
    }
}