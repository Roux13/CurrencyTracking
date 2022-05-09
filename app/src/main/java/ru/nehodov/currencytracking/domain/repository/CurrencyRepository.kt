package ru.nehodov.currencytracking.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.nehodov.currencytracking.data.base.Mapper
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.data.network.response.ApiResponse
import ru.nehodov.currencytracking.domain.model.Currency

interface CurrencyRepository {
    suspend fun updateCurrencies(apiResponseToCurrencyEntitiesMapper: Mapper<ApiResponse, List<CurrencyEntity>>): Result<Unit>
    suspend fun getCurrencies(mapper: Mapper<List<CurrencyEntity>, List<Currency>>): Flow<Result<List<Currency>>>
}