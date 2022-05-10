package ru.nehodov.currencytracking.domain.usecase

import ru.nehodov.currencytracking.domain.model.Currency
import ru.nehodov.currencytracking.domain.repository.CurrencyRepository
import javax.inject.Inject

class InvertFavouriteUseCase @Inject constructor(
    private val currencyRepository: CurrencyRepository,
) : SuspendableUseCase<InvertFavouriteUseCase.Params, Result<Unit>> {
    class Params(val currency: Currency)

    override suspend fun invoke(params: Params): Result<Unit> {
        val previousIsFavourite = params.currency.isFavorite
        return currencyRepository.updateIsFavourite(params.currency.copy(isFavorite = !previousIsFavourite))
    }
}