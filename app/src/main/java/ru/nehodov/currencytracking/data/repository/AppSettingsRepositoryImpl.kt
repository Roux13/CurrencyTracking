package ru.nehodov.currencytracking.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.nehodov.currencytracking.data.preferences.AppSettingsConst
import ru.nehodov.currencytracking.data.preferences.PreferencesDataStore
import ru.nehodov.currencytracking.data.preferences.entity.AppSettingsEntity
import ru.nehodov.currencytracking.data.providers.DispatcherProvider
import ru.nehodov.currencytracking.data.repository.AppSettingsRepositoryImpl.PreferencesKeys.BASE_CURRENCY_KEY
import ru.nehodov.currencytracking.domain.model.Currency
import ru.nehodov.currencytracking.domain.repository.AppSettingsRepository
import ru.nehodov.currencytracking.extension.asSettingsFlow
import ru.nehodov.currencytracking.extension.update
import java.io.IOException
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    private val preferenceDataStore: PreferencesDataStore,
    private val dispatcherProvider: DispatcherProvider,
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
            .flowOn(dispatcherProvider.io())

    override suspend fun settings(): AppSettingsEntity = settingsFlow().first()

    override suspend fun saveSettings(entity: AppSettingsEntity) {
        preferences.edit { preferences ->
            preferences.update(BASE_CURRENCY_KEY, entity.baseCurrency)
        }
    }

    override suspend fun saveBaseCurrency(currency: Currency): Result<Unit> {
        return try {
            preferences.edit { preferences ->
                preferences.update(BASE_CURRENCY_KEY, currency.name)
            }
            Result.success(Unit)
        } catch (ex: Exception) {
            Result.failure(ex)
        }

    }

    private fun mapPreferencesToEntity(preferences: Preferences): AppSettingsEntity {
        val baseCurrency = preferences[BASE_CURRENCY_KEY] ?: DEFAULT_BASE_CURRENCY

        return AppSettingsEntity(
            baseCurrency = baseCurrency,
        )
    }
}