package ru.nehodov.currencytracking.extension

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

suspend fun <T> DataStore<Preferences>.editItem(itemKey: Preferences.Key<T>, itemValue: T?) {
    this.edit { preferences ->
        preferences.update(itemKey, itemValue)
    }
}

fun <T> MutablePreferences.update(key: Preferences.Key<T>, value: T?) {
    value?.let {
        this[key] = it
    } ?: this.remove(key)
}