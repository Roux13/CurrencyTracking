package ru.nehodov.currencytracking.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.nehodov.currencytracking.data.preferences.entity.AppSettingsEntity
import ru.nehodov.currencytracking.domain.model.Currency

interface AppSettingsRepository {
    fun settingsFlow(): Flow<AppSettingsEntity>
    suspend fun settings(): AppSettingsEntity
    suspend fun saveSettings(entity: AppSettingsEntity)
    suspend fun saveBaseCurrency(currency: Currency): Result<Unit>
}