package ru.nehodov.currencytracking.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.nehodov.currencytracking.data.base.Mapper
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.data.network.response.ApiResponse
import ru.nehodov.currencytracking.domain.model.Currency

interface CurrencyRepository {
    suspend fun updateCurrencies(
        baseCurrency: String,
        apiResponseToCurrencyEntitiesMapper: Mapper<ApiResponse, List<CurrencyEntity>>
    ): Result<Unit>

    fun getCurrencies(mapper: Mapper<List<CurrencyEntity>, List<Currency>>): Flow<Result<List<Currency>>>

    suspend fun updateIsFavourite(currency: Currency): Result<Unit>
}