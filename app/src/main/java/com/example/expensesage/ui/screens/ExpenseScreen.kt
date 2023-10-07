package com.example.expensesage.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.data.expenses
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.components.ExpenseItem
import com.example.expensesage.ui.theme.ExpenseSageTheme
import java.util.stream.Collectors

/**
 * Composable that displays the expense screen of the app
 *
 * @param viewModel The view model that is used to store the state of the app
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseScreen(viewModel: MainViewModel) {
    val normalExpenses: List<Expense> =
        expenses.stream().filter { !it.owed }.collect(Collectors.toList())
    val groupedExpenses = viewModel.groupExpenses(normalExpenses)

//    val apiKey = BuildConfig.FILE_API_KEY

    Scaffold { it ->


        LazyColumn(contentPadding = it) {
            item {
                Spacer(modifier = Modifier.size(16.dp))
            }
            groupedExpenses.forEach {
                stickyHeader {
                    Text(text =" ${it.key.first} ${it.key.second}" )
                }
                items(it.value) { normalExpense ->
                    ExpenseItem(
                        expense = normalExpense,
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small)),
                        viewModel = viewModel
                    )
                }
            }
        }

    }
}

/**
 * Composable that displays what the UI of the app looks like in the design tab.
 */
@Preview(showSystemUi = true)
@Composable
fun ExpenseScreenPreview() {
    ExpenseSageTheme(darkTheme = false) {
        ExpenseScreen(viewModel = MainViewModel())
    }
}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
 */
@Preview
@Composable
fun ExpenseScreenDarkThemePreview() {
    ExpenseSageTheme(darkTheme = true) {
        ExpenseScreen(viewModel = MainViewModel())
    }
}
