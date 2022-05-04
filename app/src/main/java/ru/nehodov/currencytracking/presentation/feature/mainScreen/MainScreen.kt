package ru.nehodov.currencytracking.presentation.feature.mainScreen

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
        MainScreenContent(screenState.value.currencies.map { it.name })
    }
}

@Composable
private fun MainScreenContent(currencies: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(currencies) { item ->
            ListItem(currencyName = item)
        }
    }
}

@Composable
fun ListItem(currencyName: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), text = currencyName, style = MaterialTheme.typography.h5, textAlign = TextAlign.Center
        )
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
        MainScreenContent(currencies = listOf("EUR", "USD", "RUB"))
    }
}
