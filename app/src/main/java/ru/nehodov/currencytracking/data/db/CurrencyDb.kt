package ru.nehodov.currencytracking.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nehodov.currencytracking.data.db.dao.CurrencyDao
import ru.nehodov.currencytracking.data.db.dao.FavouriteCurrencyDao
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.data.db.entity.FavouriteCurrencyEntity

@Database(
    entities = [
        CurrencyEntity::class,
        FavouriteCurrencyEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class CurrencyDb : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao
    abstract fun favouriteCurrencyDao(): FavouriteCurrencyDao

}