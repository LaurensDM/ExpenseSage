package com.example.expensesage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expensesage.ui.viewModels.CurrencyUiState
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

@Composable
fun CurrencyScreen(currencyUiState: CurrencyUiState, onRetry: () -> Unit) {
    when (currencyUiState) {
        is CurrencyUiState.Loading -> Loading(modifier = Modifier.fillMaxSize())
        is CurrencyUiState.Success -> CurrenciesList(
            currencyUiState.data,
        )

        is CurrencyUiState.Error -> Error(
            onRetry,
            modifier = Modifier.fillMaxSize(),
            currencyUiState.error,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CurrenciesList(data: JsonObject) {
    var list: List<Map.Entry<String, JsonElement>> by remember {
        mutableStateOf(
            data["eur"]?.jsonObject?.entries?.toList() ?: emptyList(),
        )
    }

    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SearchBar(
            modifier = Modifier.padding(16.dp),
            query = query,
            onQueryChange = { query = it },
            onSearch = {
                keyboardController?.hide()
                active = false
                list = data["eur"]?.jsonObject?.entries?.filter {
                    it.key.contains(query.lowercase(), ignoreCase = true)
                } ?: emptyList()
            },
            active = false,
            onActiveChange = { },
            trailingIcon = {
                IconButton(onClick = {
                    keyboardController?.hide()
                    active = false
                    list = data["eur"]?.jsonObject?.entries?.filter {
                        it.key.contains(query, ignoreCase = true)
                    } ?: emptyList()
                }) {
                    Icon(Icons.Filled.Search, contentDescription = "Search")
                }
            },
        ) {
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = "Retrieval date: ${data["date"]}",
                    style = MaterialTheme.typography.labelLarge,
                    lineHeight = 24.sp,
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(list) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = "${it.key.uppercase()}",
                            style = MaterialTheme.typography.labelLarge,
                            lineHeight = 24.sp,
                        )
                    },
                    supportingContent = {
                        Text(
                            text = "${it.value}",
                            style = MaterialTheme.typography.labelMedium,
                            lineHeight = 24.sp,
                        )
                    },
                    trailingContent = {
                        Text(text = "1 EUR", style = MaterialTheme.typography.labelMedium)
                    },
                    tonalElevation = 4.dp,
                    shadowElevation = 4.dp,
                )
            }
            if (
                list.isEmpty()
            ) {
                item {
                    Text(
                        text = "No results found",
                        style = MaterialTheme.typography.labelLarge,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
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
