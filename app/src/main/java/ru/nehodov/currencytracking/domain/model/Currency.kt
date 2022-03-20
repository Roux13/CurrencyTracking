package ru.nehodov.currencytracking.domain.model

import androidx.room.ColumnInfo

data class Currency(
    val name: String,
    val rate: Double,
    val isFavorite: Boolean
)