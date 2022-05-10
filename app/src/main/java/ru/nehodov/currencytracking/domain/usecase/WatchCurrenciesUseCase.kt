package ru.nehodov.currencytracking.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.nehodov.currencytracking.domain.mapper.CurrencyEntityListToCurrencyListMapper
import ru.nehodov.currencytracking.domain.model.Currency
import ru.nehodov.currencytracking.domain.repository.CurrencyRepository
import javax.inject.Inject

class WatchCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) : UseCase<Unit, Flow<Result<List<Currency>>>> {
    override fun invoke(params: Unit): Flow<Result<List<Currency>>> {
        return currencyRepository.getCurrencies(CurrencyEntityListToCurrencyListMapper())
    }
}