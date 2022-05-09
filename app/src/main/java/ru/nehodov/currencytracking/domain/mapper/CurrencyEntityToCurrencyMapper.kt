package ru.nehodov.currencytracking.domain.mapper

import ru.nehodov.currencytracking.data.base.Mapper
import ru.nehodov.currencytracking.data.db.entity.CurrencyEntity
import ru.nehodov.currencytracking.domain.model.Currency

class CurrencyEntityListToCurrencyListMapper : Mapper<List<CurrencyEntity>, List<Currency>> {
    override fun map(from: List<CurrencyEntity>): List<Currency> {
        return from.map { entity ->
            Currency(
                name = entity.name,
                rate = entity.rate,
                isFavorite = false,
            )
        }
    }
}