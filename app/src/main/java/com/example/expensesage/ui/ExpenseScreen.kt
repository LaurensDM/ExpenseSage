package com.example.expensesage.ui;

import androidx.compose.runtime.Composable
import com.example.expensesage.BuildConfig
import com.example.expensesage.data.Expense
import com.example.expensesage.data.expenses
import java.util.stream.Collectors

@Composable
fun ExpenseScreen() {
    val normalExpenses : List<Expense> = expenses.stream().filter { !it.owed }.collect(Collectors.toList())


    val apiKey = BuildConfig.FILE_API_KEY
}
