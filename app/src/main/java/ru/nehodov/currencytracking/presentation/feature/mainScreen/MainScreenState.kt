package ru.nehodov.currencytracking.presentation.feature.mainScreen

import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity

data class MainScreenState(
    val baseCurrency: String,
    val selectedTab: MainScreenTab,
    val currencies: List<CurrencyEntity>
)