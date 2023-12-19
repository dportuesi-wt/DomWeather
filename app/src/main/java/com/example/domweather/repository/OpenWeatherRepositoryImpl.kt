package com.example.domweather.repository

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class OpenWeatherRepositoryImpl(
    private val context: Context
) : OpenWeatherRepository {

    override suspend fun updateStoredTemp(cityName: String) {
        storeCurrentTemp( (0..70).random())
    }

    override fun getStoredTemp(cityName: String): Flow<Int> {
        return getPreference(PreferencesKeys.CURRENT_TEMP, 0)
    }

    private suspend fun storeCurrentTemp(temp: Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.CURRENT_TEMP] = temp
        }
    }

    private fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):
            Flow<T> = context.dataStore.data.catch { exception ->
        if (exception is IOException){
            emit(emptyPreferences())
        }else{
            throw exception
        }
    }.map { preferences->
        val result = preferences[key]?: defaultValue
        result
    }

    /* This returns the last saved value of the key. If we change the value,
        it wont effect the values produced by this function */
    private suspend fun <T> getFirstPreference(key: Preferences.Key<T>, defaultValue: T) :
            T = context.dataStore.data.first()[key] ?: defaultValue


    private object PreferencesKeys {
        val CURRENT_TEMP = intPreferencesKey("CURRENT_TEMP")
    }
}


private val Context.dataStore by preferencesDataStore(
    name = "dw_weather_datastore"
)