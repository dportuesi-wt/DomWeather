package com.example.domweather

import android.app.Application
import com.example.domweather.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(mainModule)
        }
    }
}