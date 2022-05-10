package ru.nehodov.currencytracking.data.mappers

import ru.nehodov.currencytracking.data.base.Mapper
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.data.network.response.ApiResponse

class ApiResponseToCurrencyListMapper : Mapper<ApiResponse, List<CurrencyEntity>> {
    override fun map(from: ApiResponse): List<CurrencyEntity> {
        return from.rates?.map { (currencyName, currencyRate) ->
            CurrencyEntity(name = currencyName, rate = currencyRate, isFavourite = null)
        } ?: emptyList()
    }
}