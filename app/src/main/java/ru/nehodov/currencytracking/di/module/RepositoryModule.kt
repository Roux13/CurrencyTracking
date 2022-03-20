package ru.nehodov.currencytracking.di.module

import dagger.Module
import dagger.Provides
import ru.nehodov.currencytracking.data.db.CurrencyDb
import ru.nehodov.currencytracking.data.network.ApiService
import ru.nehodov.currencytracking.data.preferences.PreferencesDataStore
import ru.nehodov.currencytracking.data.repository.AppSettingsRepository
import ru.nehodov.currencytracking.data.repository.AppSettingsRepositoryImpl
import ru.nehodov.currencytracking.data.repository.CurrencyRepository
import ru.nehodov.currencytracking.data.repository.CurrencyRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCurrencyRepository(
        apiService: ApiService,
        db: CurrencyDb,
        appSettingsRepository: AppSettingsRepository,
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(
            apiService = apiService,
            currencyDao = db.currencyDao(),
            appSettingsRepository = appSettingsRepository
        )
    }

    @Singleton
    @Provides
    fun provideAppSettingsRepository(preferencesDataStore: PreferencesDataStore): AppSettingsRepository {
        return AppSettingsRepositoryImpl(preferencesDataStore)
    }
}