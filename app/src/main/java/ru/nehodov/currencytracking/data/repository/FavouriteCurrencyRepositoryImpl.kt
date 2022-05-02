package ru.nehodov.currencytracking.data.repository

import ru.nehodov.currencytracking.data.db.dao.FavouriteCurrencyDao
import ru.nehodov.currencytracking.domain.repository.FavouriteCurrencyRepository
import javax.inject.Inject

class FavouriteCurrencyRepositoryImpl @Inject constructor(
    private val dao: FavouriteCurrencyDao
) : FavouriteCurrencyRepository {

}