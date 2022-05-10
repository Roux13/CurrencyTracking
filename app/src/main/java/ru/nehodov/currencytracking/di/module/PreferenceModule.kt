package ru.nehodov.currencytracking.di.module

import dagger.Binds
import dagger.Module
import ru.nehodov.currencytracking.data.preferences.PreferencesDataStore
import ru.nehodov.currencytracking.data.preferences.PreferencesDataStoreImpl

@Module
abstract class PreferenceModule {

    @AppScope
    @Binds
    abstract fun bindPreferencesDataStore(preferencesDataStore: PreferencesDataStoreImpl): PreferencesDataStore
}