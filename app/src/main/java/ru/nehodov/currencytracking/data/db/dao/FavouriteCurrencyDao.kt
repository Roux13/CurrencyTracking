package ru.nehodov.currencytracking.data.db.dao

import androidx.room.*
import ru.nehodov.currencytracking.data.db.entity.FavouriteCurrencyEntity

@Dao
interface FavouriteCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: FavouriteCurrencyEntity): Long

    @Query("SELECT * FROM favourite_currency")
    fun getAll(): List<FavouriteCurrencyEntity>

    @Delete
    fun delete(entity: FavouriteCurrencyEntity)
}