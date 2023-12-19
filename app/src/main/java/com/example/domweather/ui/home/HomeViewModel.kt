package com.example.domweather.ui.home

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.updateAll
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domweather.repository.OpenWeatherRepository
import com.example.domweather.widget.TemperatureWidget
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val openWeatherRepository: OpenWeatherRepository,
    private val context: Context,
): ViewModel() {

    val currentLocalTemp: Flow<Int> = openWeatherRepository.getStoredTemp("Indianapolis")

    init {
        viewModelScope.launch {
            openWeatherRepository.updateStoredTemp("Indianapolis")
            updateWidget()
        }
    }

    fun syncLocalTemp() {
        viewModelScope.launch {
            openWeatherRepository.updateStoredTemp("Indianapolis")
            updateWidget()
        }
    }

    private suspend fun updateWidget() {
        val manager = GlanceAppWidgetManager(context)
        val widget = TemperatureWidget()
        val glanceIds = manager.getGlanceIds(widget.javaClass)
        glanceIds.forEach { glanceId ->
            widget.update(context, glanceId)
        }
    }
}