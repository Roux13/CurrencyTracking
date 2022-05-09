package ru.nehodov.currencytracking.presentation.feature.mainScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import ru.nehodov.currencytracking.domain.usecase.UpdateCurrenciesUseCase
import ru.nehodov.currencytracking.domain.usecase.WatchCurrenciesUseCase
import ru.nehodov.currencytracking.extension.launchCoroutine
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val updateCurrenciesUseCase: UpdateCurrenciesUseCase,
    private val watchCurrenciesUseCase: WatchCurrenciesUseCase,
) : ViewModel() {

    private val _screenStateFlow: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(
            baseCurrency = "EUR",
            selectedTab = MainScreenTab.ALL_CURRENCIES,
            currencies = emptyList()
        )
    )

    init {
        launchCoroutine {
            watchCurrenciesUseCase.invoke(Unit)
                .onEach { result ->
                    result
                        .onSuccess { currencies ->
                            _screenStateFlow.value.let { currentScreenState ->
                                updateScreenState(currentScreenState.copy(currencies = currencies.filterNot {
                                    it.name.equals(
                                        currentScreenState.baseCurrency,
                                        ignoreCase = true
                                    )
                                }))
                            }
                        }
                        .onFailure {
                            print(it.message)
                        }
                }.collect()
        }
    }

    fun screenStateFlow() = _screenStateFlow.asStateFlow()

    fun selectTab(selectedTab: MainScreenTab) {
        updateScreenState(
            _screenStateFlow.value.copy(
                selectedTab = selectedTab
            )
        )
    }

    fun selectBaseCurrency(selectedCurrency: String) {
        updateScreenState(
            _screenStateFlow.value.copy(
                baseCurrency = selectedCurrency
            )
        )
    }

    fun updateCurrencies() {
        launchCoroutine {
            updateCurrenciesUseCase(Unit)
        }
    }

    private fun updateScreenState(newScreenState: MainScreenState) {
        launchCoroutine { _screenStateFlow.emit(newScreenState) }
    }
}