package com.example.expensesage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.CurrencyText
import com.example.expensesage.ui.components.ExpenseItemHome
import com.example.expensesage.ui.theme.ExpenseSageTheme
import com.example.expensesage.ui.viewModels.ListUiState
import com.example.expensesage.ui.viewModels.ListViewModel
import com.example.expensesage.ui.viewModels.SettingsViewModel

/**
 * Composable that displays the start screen of the app
 *
 * @param modifier Modifier to apply to this layout node.
 */
@Composable
fun StartScreen(
    listViewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    listViewModel.get5Expenses()

    Scaffold(topBar = {
        ExpenseSageTopAppBar()
    }) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            ) {
            Spacer(modifier = Modifier.size(32.dp))
            TopTile()
            BottomTile(listViewModel.listUiState, retry = { listViewModel.get5Expenses() })
        }

    }

}

/**
 * Composable that displays the top app bar of the app
 *
 * @param modifier Modifier to apply to this layout node.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseSageTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small)),
                    painter = painterResource(R.drawable.logo),

                    // Content Description is not needed here - image is decorative, and setting a
                    // null content description allows accessibility services to skip this element
                    // during navigation.

                    contentDescription = null,
                )
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        },
        modifier = modifier,
    )
}

/**
 * Composable that displays the top tile of the start screen. Has information about the amount of money left.
 *
 */
@Composable
fun TopTile(settingsViewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically,
            ),
        ) {
            Image(
                modifier = Modifier
                    .size(82.dp)
                    .padding(dimensionResource(R.dimen.padding_small)),
                painter = painterResource(R.drawable.money),

                // Content Description is not needed here - image is decorative, and setting a
                // null content description allows accessibility services to skip this element
                // during navigation.

                contentDescription = null,
            )
            CurrencyText(
                currency = settingsViewModel.getCurrency(),
                moneyAvailable = settingsViewModel.getMoneyAvailable(),
                settingsViewModel.getCurrencyModifier()
            )
        }
    }
}

@Composable
fun BottomTile(listUiState: ListUiState, retry: () -> Unit = {}) {
    when (listUiState) {
        is ListUiState.Success -> {
            TopExpensesList(listUiState.expenses)
        }

        is ListUiState.Error -> {
            Error(retryAction = retry, error = listUiState.message)
        }

        is ListUiState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Loading()
            }

        }
    }
}

@Composable
fun TopExpensesList(expenses: List<Expense>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        item { Spacer(modifier = Modifier.size(24.dp)) }
        item {
            Text(
                text = "Latest expenses",
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                textAlign = TextAlign.Center,
            )
        }
        if (expenses.isEmpty()) {
            item {
                Text(
                    text = "No expenses yet",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                    textAlign = TextAlign.Center,
                )
            }
        } else {
            items(expenses, key = { it.id }) { expense ->
                ExpenseItemHome(expense)
            }
        }
        item { Spacer(modifier = Modifier.size(8.dp)) }
    }
}

/**
 * Composable that displays what the UI of the app looks like in the design tab.
 */
@Preview(showSystemUi = true)
@Composable
fun ExpensePreview() {
    ExpenseSageTheme(darkTheme = false) {
        StartScreen()
    }
}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
 */
@Preview
@Composable
fun ExpenseDarkThemePreview() {
    ExpenseSageTheme(darkTheme = true) {
        StartScreen()
    }
}
