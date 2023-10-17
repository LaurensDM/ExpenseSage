package com.example.expensesage.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.components.ExpenseList
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.viewModels.ListViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OwedScreen(
    viewModel: MainViewModel,
    dataViewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by dataViewModel.getExpenses(true).collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {viewModel.showModal(modalType = ModalType.CREATE, owed = true)} ) {
                Icon(Icons.Default.Add, contentDescription = "Add expense")
            }
//                ExpenseSageFloatingActionButton(onAddClicked = onCreateClicked)
        },
    ) { it ->

        ExpenseList(it = it, groupedExpenses = uiState.expenses, viewModel = viewModel)

    }

}