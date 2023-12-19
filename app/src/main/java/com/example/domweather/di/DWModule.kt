package com.example.domweather.di

import com.example.domweather.repository.OpenWeatherRepository
import com.example.domweather.repository.OpenWeatherRepositoryImpl
import com.example.domweather.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single<OpenWeatherRepository> { OpenWeatherRepositoryImpl(
        context = get(),
    ) }

    viewModel {
        HomeViewModel(
            openWeatherRepository = get(),
            context = get()
        )
    }
}