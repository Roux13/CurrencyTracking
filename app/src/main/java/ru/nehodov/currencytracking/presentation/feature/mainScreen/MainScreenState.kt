package ru.nehodov.currencytracking.presentation.feature.mainScreen

data class MainScreenState(
    val baseCurrency: String,
    val selectedTab: MainScreenTab,
    val isDropDownMenuExpanded: Boolean,
)