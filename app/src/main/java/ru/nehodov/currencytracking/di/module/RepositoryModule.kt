package ru.nehodov.currencytracking.di.module

import dagger.Binds
import dagger.Module
import ru.nehodov.currencytracking.data.repository.AppSettingsRepositoryImpl
import ru.nehodov.currencytracking.data.repository.CurrencyRepositoryImpl
import ru.nehodov.currencytracking.domain.repository.AppSettingsRepository
import ru.nehodov.currencytracking.domain.repository.CurrencyRepository

@Module
abstract class RepositoryModule {

    @AppScope
    @Binds
    abstract fun provideCurrencyRepository(
        currencyRepository: CurrencyRepositoryImpl
    ): CurrencyRepository

    @AppScope
    @Binds
    abstract fun provideAppSettingsRepository(appSettingsRepository: AppSettingsRepositoryImpl): AppSettingsRepository
}