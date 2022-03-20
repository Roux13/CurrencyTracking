package ru.nehodov.currencytracking.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.distinctUntilChanged

fun <T> Flow<T>.asSettingsFlow() = this.distinctUntilChanged().cancellable()