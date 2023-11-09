package com.example.expensesage.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.viewModels.CurrencyUIState
import com.example.expensesage.ui.viewModels.CurrencyViewModel
import kotlinx.coroutines.launch

@Composable
fun CurrencyScreen(currencyViewModel: CurrencyViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    when (currencyViewModel.currencyUIState) {
        is CurrencyUIState.Loading -> Loading(modifier = Modifier.fillMaxSize())
        is CurrencyUIState.Success -> CurrenciesList(
            date = (currencyViewModel.currencyUIState as CurrencyUIState.Success).date,
        )

        is CurrencyUIState.Error -> Error(
            { currencyViewModel.getData() },
            modifier = Modifier.fillMaxSize(),
            (currencyViewModel.currencyUIState as CurrencyUIState.Error).error,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CurrenciesList(
    date: String,
    currencyViewModel: CurrencyViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val listState = rememberLazyListState()

    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SearchBar(
            modifier = Modifier.padding(16.dp),
            query = currencyViewModel.queryState,
            onQueryChange = { currencyViewModel.updateQuery(it) },
            onSearch = {
                keyboardController?.hide()
                currencyViewModel.search()
            },
            active = false,
            onActiveChange = { },
            trailingIcon = {
                IconButton(onClick = {
                    keyboardController?.hide()
                    currencyViewModel.search()
                }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            },
        ) {
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
        ) {
            item {
                Text(
                    text = "Retrieval date: $date",
                    style = MaterialTheme.typography.displayMedium,
                    lineHeight = 24.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(currencyViewModel.list, key = { it.currencyCode }) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = it.currencyCode.uppercase(),
                            style = MaterialTheme.typography.displaySmall,
                            lineHeight = 24.sp,
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "${it.rate}",
                            style = MaterialTheme.typography.labelMedium,
                            lineHeight = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    },
                    trailingContent = {
                        Text(
                            text = "1 ${it.comparedCurrency.uppercase()}",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp,
                )
            }
            if (
                currencyViewModel.list.isEmpty()
            ) {
                item {
                    Text(
                        text = "No results found ",
                        style = MaterialTheme.typography.labelLarge,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
    AnimatedVisibility(visible = showButton) {
        ScrollToTop(goToTop = {
            scope.launch {
                listState.animateScrollToItem(0)
            }
        })
    }
}

@Composable
fun ScrollToTop(goToTop: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .size(50.dp)
                .align(Alignment.BottomEnd),
            onClick = goToTop,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ) {
            Icon(
                Icons.Default.ArrowUpward,
                contentDescription = "go to top"
            )
        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        CircularProgressIndicator(modifier = Modifier.size(60.dp), strokeWidth = 6.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Loading...",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun Error(retryAction: () -> Unit, modifier: Modifier = Modifier, error: String) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.error,
            lineHeight = 24.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = retryAction) {
            Text(text = "Retry")
        }
    }
}
