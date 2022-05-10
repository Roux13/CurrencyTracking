package ru.nehodov.currencytracking.presentation.feature.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nehodov.currencytracking.R
import ru.nehodov.currencytracking.domain.model.Currency

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.updateCurrencies()
    }

    val screenState by viewModel.screenStateFlow().collectAsState()
    val currencies by viewModel.currenciesFlow.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MainTopBar(
                baseCurrency = screenState.baseCurrency,
                currencies = currencies,
                isMenuExpanded = screenState.isDropDownMenuExpanded,
                onClick = { viewModel.expandDropDownMenu() },
                onSelect = { viewModel.selectBaseCurrency(it) },
                onDismiss = { viewModel.collapseDropDownMenu() },
            )
        },
        bottomBar = {

        }
    ) {
        MainScreenContent(
            currencies = currencies,
            onFavouriteClick = { viewModel.updateIsFavourite(it) }
        )
    }
}

@Composable
private fun MainScreenContent(currencies: List<Currency>, onFavouriteClick: (Currency) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(currencies) { item ->
            ListItem(currency = item, onFavouriteClick)
        }
    }
}

@Composable
fun ListItem(
    currency: Currency,
    onFavouriteClick: (Currency) -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = currency.name,
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                text = currency.rate.toString(),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )
            IconButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    onFavouriteClick(currency)
                }
            ) {
                if (currency.isFavorite) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_24), contentDescription = null, tint = Color.Red
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_favorite_border_24),
                        contentDescription = null,
                        tint = Color.Red
                    )
                }
            }
        }

    }
}

@Composable
private fun MainTopBar(
    baseCurrency: String,
    currencies: List<Currency>,
    isMenuExpanded: Boolean,
    onClick: () -> Unit,
    onSelect: (Currency) -> Unit,
    onDismiss: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = baseCurrency, style = MaterialTheme.typography.body1, textAlign = TextAlign.Center
            )
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = isMenuExpanded, onDismissRequest = { onDismiss() }) {
            currencies.forEach { currency ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { onSelect(currency) }) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = currency.name, style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
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
                Currency("USD", rate = 0.9, isFavorite = true),
                Currency("RUB", rate = 83.6, isFavorite = false),
            ),
            onFavouriteClick = {}
        )
    }
}
