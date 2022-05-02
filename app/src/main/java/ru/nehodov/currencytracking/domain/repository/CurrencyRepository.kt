package ru.nehodov.currencytracking.domain.repository

import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.data.mappers.IEntityMapper
import ru.nehodov.currencytracking.data.network.response.ApiResponse

interface CurrencyRepository {
    suspend fun updateCurrencies(apiResponseToCurrencyEntitiesMapper: IEntityMapper<ApiResponse, List<CurrencyEntity>>): Result<Unit>
}