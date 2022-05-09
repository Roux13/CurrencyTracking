package ru.nehodov.currencytracking.domain.usecase

import ru.nehodov.currencytracking.data.mappers.ApiResponseToCurrencyListMapper
import ru.nehodov.currencytracking.domain.repository.CurrencyRepository
import javax.inject.Inject

class UpdateCurrenciesUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) : UseCase<Unit, Result<Unit>> {
    override suspend fun invoke(params: Unit): Result<Unit> {
        return currencyRepository.updateCurrencies(ApiResponseToCurrencyListMapper())
    }
}