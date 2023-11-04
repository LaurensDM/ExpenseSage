package com.example.expensesage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.ExpenseList
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.viewModels.ListViewModel
import com.example.expensesage.ui.viewModels.MapUiState

/**
 * Composable that displays the expense screen of the app
 *
 * @param viewModel The view model that is used to store the state of the app
 */
@Composable
fun ExpenseScreen(
    showModal: (expense: Expense?, isOwed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: String, onCancel: () -> Unit) -> Unit,
    viewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    viewModel.getExpenses(false)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showModal(null, false, ModalType.CREATE) }) {
                Icon(Icons.Default.Add, contentDescription = "Add expense")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExpensesView(
                mapUiState = viewModel.mapUiState,
                { viewModel.getExpenses(false) },
                showModal,
                showAlert
            )
        }
    }
}

@Composable
fun ExpensesView(
    mapUiState: MapUiState,
    retry: () -> Unit = {},
    showModal: (expense: Expense?, isOwed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: String, onCancel: () -> Unit) -> Unit,
) {
    when (mapUiState) {
        is MapUiState.Loading ->
    Loading(modifier = Modifier.fillMaxSize())
        is MapUiState.Success -> {
            val expenses by mapUiState.expenses.collectAsState()
            ExpenseList(expenses, showModal, showAlert)
        }

        is MapUiState.Error -> Error(
            retry,
            modifier = Modifier.fillMaxSize(),
            mapUiState.message,
        )
    }
}

