package com.example.expensesage.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.expensesage.data.Expense
import com.example.expensesage.data.expenses
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.components.ExpenseList
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
        ExpenseList(it = it, groupedExpenses = groupedExpenses, viewModel = viewModel)

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
