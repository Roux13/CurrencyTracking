package ru.nehodov.currencytracking.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

interface PreferencesDataStore {
    val appPreferences: DataStore<Preferences>
}

class PreferencesDataStoreImpl(private val context: Context) : PreferencesDataStore {

    companion object {
        private const val APP_PREFERENCES_STORE_NAME = "app_preferences"

        private val Context.appPreferencesStore by preferencesDataStore(
            name = APP_PREFERENCES_STORE_NAME
        )
    }

    override val appPreferences: DataStore<Preferences>
        get() = context.appPreferencesStore
}