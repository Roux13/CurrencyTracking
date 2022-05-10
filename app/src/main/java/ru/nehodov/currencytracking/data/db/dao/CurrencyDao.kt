package ru.nehodov.currencytracking.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg currencyEntity: CurrencyEntity)

    @Query(
        """
        UPDATE 
            currency 
        SET 
            rate = :newRate
        WHERE
            name = :name 
    """
    )
    suspend fun update(name: String, newRate: Double): Int

    @Transaction
    suspend fun updateOrInsert(currencyEntity: CurrencyEntity) {
        val updated = update(currencyEntity.name, currencyEntity.rate)
        if (updated == 0) {
            insert(currencyEntity)
        }
    }

    @Query("SELECT * FROM currency")
    fun getAll(): Flow<List<CurrencyEntity>>

    @Query(
        """
        UPDATE 
            currency 
        SET 
            isFavourite = :isFavourite
        WHERE
            name = :name 
    """
    )
    suspend fun updateIsFavourite(name: String, isFavourite: Boolean): Int
}