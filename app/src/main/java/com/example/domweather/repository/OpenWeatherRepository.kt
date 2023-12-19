package com.example.domweather.repository

import kotlinx.coroutines.flow.Flow

interface OpenWeatherRepository {

    suspend fun updateStoredTemp(cityName: String)

    fun getStoredTemp(cityName: String): Flow<Int>
}