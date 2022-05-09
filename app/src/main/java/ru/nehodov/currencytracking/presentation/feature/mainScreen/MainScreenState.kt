package ru.nehodov.currencytracking.presentation.feature.mainScreen

import ru.nehodov.currencytracking.domain.model.Currency

data class MainScreenState(
    val baseCurrency: String,
    val selectedTab: MainScreenTab,
    val currencies: List<Currency>
)