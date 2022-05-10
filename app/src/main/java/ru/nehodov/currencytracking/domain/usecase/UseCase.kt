package ru.nehodov.currencytracking.domain.usecase

interface SuspendableUseCase<Params, Out> {

    suspend operator fun invoke(params: Params): Out
}

interface UseCase<Params, Out> {

    operator fun invoke(params: Params): Out
}