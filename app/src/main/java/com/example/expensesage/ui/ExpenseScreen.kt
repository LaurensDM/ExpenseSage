package com.example.expensesage.ui;

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.expensesage.BuildConfig
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.data.expenses
import com.example.expensesage.ui.components.ExpenseItem
import com.example.expensesage.ui.theme.ExpenseSageTheme
import java.util.stream.Collectors

@Composable
fun ExpenseScreen() {
    val normalExpenses : List<Expense> = expenses.stream().filter { !it.owed }.collect(Collectors.toList())


    val apiKey = BuildConfig.FILE_API_KEY

    Scaffold { it ->
        LazyColumn(contentPadding = it) {
            items(normalExpenses) { normalExpense ->
                ExpenseItem(expense = normalExpense, modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ExpenseScreenPreview() {
    ExpenseSageTheme(darkTheme = false) {
        ExpenseScreen()
    }
}

/**
 * Composable that displays what the UI of the app looks like in dark theme in the design tab.
 */
@Preview
@Composable
fun ExpenseScreenDarkThemePreview() {
    ExpenseSageTheme(darkTheme = true) {
        ExpenseScreen()
    }
}
