package ru.nehodov.currencytracking.domain.usecase

interface UseCase<Params, Out> {

    suspend operator fun invoke(params: Params): Out
}
