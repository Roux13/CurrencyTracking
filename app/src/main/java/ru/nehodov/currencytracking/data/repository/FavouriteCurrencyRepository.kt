package ru.nehodov.currencytracking.data.repository

import ru.nehodov.currencytracking.data.db.dao.FavouriteCurrencyDao

interface FavouriteCurrencyRepository {
}

class FavouriteCurrencyRepositoryImpl(
    private val dao: FavouriteCurrencyDao
): FavouriteCurrencyRepository {

}