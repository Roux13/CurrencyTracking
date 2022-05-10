package ru.nehodov.currencytracking.domain.usecase

import ru.nehodov.currencytracking.domain.model.Currency
import ru.nehodov.currencytracking.domain.repository.AppSettingsRepository
import javax.inject.Inject

class SaveBaseCurrencyUseCase @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository,
) : SuspendableUseCase<SaveBaseCurrencyUseCase.Params, Result<Unit>> {
    class Params(val baseCurrency: Currency)

    override suspend fun invoke(params: Params): Result<Unit> {
        return appSettingsRepository.saveBaseCurrency(params.baseCurrency)
    }
}