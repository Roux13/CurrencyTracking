package ru.nehodov.currencytracking.presentation.feature.mainScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nehodov.currencytracking.domain.model.Currency

enum class MainScreenTab {
    ALL_CURRENCIES,
    FAVOURITES
}

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {
    val screenState = viewModel.screenStateFlow().collectAsState()

    LaunchedEffect(Unit) {
        viewModel.updateCurrencies()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

        },
        bottomBar = {

        }
    ) {
        MainScreenContent(screenState.value.currencies)
    }
}

@Composable
private fun MainScreenContent(currencies: List<Currency>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(currencies) { item ->
            ListItem(currency = item)
        }
    }
}

@Composable
fun ListItem(currency: Currency) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp), text = currency.name, style = MaterialTheme.typography.h5, textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp), text = currency.rate.toString(), style = MaterialTheme.typography.h5, textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
private fun MainTopBar() {

}

@Composable
private fun MainBottomBar() {

}

@Preview
@Composable
fun MainScreenContentPreview() {
    MaterialTheme {
        MainScreenContent(
            currencies = listOf(
                Currency("EUR", rate = 1.0, isFavorite = false),
                Currency("USD", rate = 0.9, isFavorite = false),
                Currency("RUB", rate = 83.6, isFavorite = false),
            )
        )
    }
}
