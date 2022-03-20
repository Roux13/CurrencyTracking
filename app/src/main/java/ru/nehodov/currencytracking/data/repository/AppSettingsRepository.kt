package ru.nehodov.currencytracking.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.nehodov.currencytracking.data.preferences.AppSettingsConst
import ru.nehodov.currencytracking.data.preferences.PreferencesDataStore
import ru.nehodov.currencytracking.data.preferences.entity.AppSettingsEntity
import ru.nehodov.currencytracking.data.repository.AppSettingsRepositoryImpl.PreferencesKeys.BASE_CURRENCY_KEY
import ru.nehodov.currencytracking.extension.asSettingsFlow
import ru.nehodov.currencytracking.extension.update
import java.io.IOException

interface AppSettingsRepository {
    fun settingsFlow(): Flow<AppSettingsEntity>
    suspend fun settings(): AppSettingsEntity
    suspend fun saveSettings(entity: AppSettingsEntity)
}

class AppSettingsRepositoryImpl(
    private val preferenceDataStore: PreferencesDataStore
) : AppSettingsRepository {

    private companion object {
        const val DEFAULT_BASE_CURRENCY = "EUR"
    }

    private object PreferencesKeys {
        val BASE_CURRENCY_KEY = stringPreferencesKey(AppSettingsConst.BASE_CURRENCY)
    }

    private val preferences: DataStore<Preferences>
        get() = preferenceDataStore.appPreferences

    override fun settingsFlow(): Flow<AppSettingsEntity> =
        preferences.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { appPreferences ->
                mapPreferencesToEntity(appPreferences)
            }.asSettingsFlow()

    override suspend fun settings(): AppSettingsEntity = settingsFlow().first()

    override suspend fun saveSettings(entity: AppSettingsEntity) {
        preferences.edit { preferences ->
            preferences.update(BASE_CURRENCY_KEY, entity.baseCurrency)
        }
    }

    private fun mapPreferencesToEntity(preferences: Preferences): AppSettingsEntity {
        val baseCurrency = preferences[BASE_CURRENCY_KEY] ?: DEFAULT_BASE_CURRENCY

        return AppSettingsEntity(
            baseCurrency = baseCurrency,
        )
    }
}