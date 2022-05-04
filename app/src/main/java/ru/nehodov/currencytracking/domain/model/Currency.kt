package ru.nehodov.currencytracking.domain.model

data class Currency(
    val name: String,
    val rate: Double,
    val isFavorite: Boolean
)