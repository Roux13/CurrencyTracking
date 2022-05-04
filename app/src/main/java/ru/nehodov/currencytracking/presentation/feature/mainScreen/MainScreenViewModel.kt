package ru.nehodov.currencytracking.presentation.feature.mainScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import ru.nehodov.currencytracking.data.mappers.ApiResponseToCurrencyListMapper
import ru.nehodov.currencytracking.domain.repository.CurrencyRepository
import ru.nehodov.currencytracking.extension.launchCoroutine
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
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
            currencyRepository.getCurrencies()
                .onSuccess { currencies ->
                    updateScreenState(_screenStateFlow.value.copy(currencies = currencies))
                }
                .onFailure {
                    print(it.message)
                }
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
            withContext(Dispatchers.IO) {
                currencyRepository.updateCurrencies(ApiResponseToCurrencyListMapper())
            }
        }
    }

    private fun updateScreenState(newScreenState: MainScreenState) {
        launchCoroutine { _screenStateFlow.emit(newScreenState) }
    }
}