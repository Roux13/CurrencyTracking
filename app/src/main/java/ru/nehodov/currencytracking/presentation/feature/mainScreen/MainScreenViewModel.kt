package ru.nehodov.currencytracking.presentation.feature.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import ru.nehodov.currencytracking.domain.model.Currency
import ru.nehodov.currencytracking.domain.usecase.InvertFavouriteUseCase
import ru.nehodov.currencytracking.domain.usecase.SaveBaseCurrencyUseCase
import ru.nehodov.currencytracking.domain.usecase.UpdateCurrenciesUseCase
import ru.nehodov.currencytracking.domain.usecase.WatchCurrenciesUseCase
import ru.nehodov.currencytracking.domain.usecase.WatchSettingsUseCase
import ru.nehodov.currencytracking.extension.launchCoroutine
import javax.inject.Inject

enum class MainScreenTab {
    ALL_CURRENCIES,
    FAVOURITES
}

class MainScreenViewModel @Inject constructor(
    private val updateCurrenciesUseCase: UpdateCurrenciesUseCase,
    private val watchCurrenciesUseCase: WatchCurrenciesUseCase,
    private val watchSettingsUseCase: WatchSettingsUseCase,
    private val saveBaseCurrencyUseCase: SaveBaseCurrencyUseCase,
    private val invertFavouriteUseCase: InvertFavouriteUseCase,
) : ViewModel() {


    val currenciesFlow: Flow<List<Currency>> = watchCurrenciesUseCase.invoke(Unit)
        .map { result ->
            result.fold(
                onSuccess = { currencies ->
                    currencies
                },
                onFailure = {
                    print(it.message)
                    emptyList()
                }
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList(),
        )

    private val _screenStateFlow: MutableStateFlow<MainScreenState> = MutableStateFlow(
        MainScreenState(
            baseCurrency = "EUR",
            selectedTab = MainScreenTab.ALL_CURRENCIES,
            isDropDownMenuExpanded = false,
        )
    )

    init {
        launchCoroutine {
            watchSettingsUseCase.invoke(Unit)
                .onEach {
                    updateScreenState(_screenStateFlow.value.copy(baseCurrency = it.baseCurrency))
                }
                .collect()
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

    fun expandDropDownMenu() {
        updateScreenState(
            _screenStateFlow.value.copy(
                isDropDownMenuExpanded = true
            )
        )
    }

    fun collapseDropDownMenu() {
        updateScreenState(
            _screenStateFlow.value.copy(
                isDropDownMenuExpanded = false
            )
        )
    }

    fun selectBaseCurrency(selectedCurrency: Currency) {
        collapseDropDownMenu()
        launchCoroutine {
            saveBaseCurrencyUseCase.invoke(SaveBaseCurrencyUseCase.Params(selectedCurrency))
                .onSuccess {
                    updateCurrencies()
                }
        }
    }

    fun updateCurrencies() {
        launchCoroutine {
            updateCurrenciesUseCase(Unit)
        }
    }

    fun updateIsFavourite(currency: Currency) {
        launchCoroutine {
            invertFavouriteUseCase.invoke(InvertFavouriteUseCase.Params(currency))
        }
    }

    private fun updateScreenState(newScreenState: MainScreenState) {
        launchCoroutine { _screenStateFlow.emit(newScreenState) }
    }
}