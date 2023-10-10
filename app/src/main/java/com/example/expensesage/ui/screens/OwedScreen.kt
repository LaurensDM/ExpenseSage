package com.example.expensesage.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.example.expensesage.data.Expense
import com.example.expensesage.data.expenses
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.components.ExpenseList
import java.util.stream.Collectors


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OwedScreen(viewModel: MainViewModel) {
    val normalExpenses: List<Expense> =
        expenses.stream().filter { it.owed }.collect(Collectors.toList())
    val groupedExpenses = viewModel.groupExpenses(normalExpenses)

    Scaffold { it ->

        ExpenseList(it = it, groupedExpenses = groupedExpenses, viewModel = viewModel)

    }

}