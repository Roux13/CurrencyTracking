package ru.nehodov.currencytracking.data.providers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

interface DispatcherProvider {
    fun io(): CoroutineDispatcher
    fun computation(): CoroutineDispatcher
    fun ui(): MainCoroutineDispatcher
}

class DispatcherProviderImpl : DispatcherProvider {
    override fun io() = Dispatchers.IO
    override fun computation() = Dispatchers.Default
    override fun ui() = Dispatchers.Main.immediate
}