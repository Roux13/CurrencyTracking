package ru.nehodov.currencytracking.data.providers

import ru.nehodov.currencytracking.BuildConfig

interface BuildInfoProvider {
    val apiKey: String
}

class BuildInfoProviderImpl: BuildInfoProvider {
    override val apiKey: String = BuildConfig.API_KEY
}