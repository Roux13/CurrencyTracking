package ru.nehodov.currencytracking.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_currency")
data class FavouriteCurrencyEntity(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String
)