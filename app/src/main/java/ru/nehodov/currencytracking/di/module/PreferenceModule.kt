package ru.nehodov.currencytracking.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.nehodov.currencytracking.data.preferences.PreferencesDataStore
import ru.nehodov.currencytracking.data.preferences.PreferencesDataStoreImpl

@Module
object PreferenceModule {

    @AppScope
    @Provides
    fun providePreferencesDataStore(context: Context): PreferencesDataStore {
        return PreferencesDataStoreImpl(context)
    }
}