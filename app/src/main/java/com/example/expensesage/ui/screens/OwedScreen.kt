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

@Composable
fun OwedScreen(
    showModal: (expense: Expense?, owed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: String, onCancel: () -> Unit) -> Unit,
    viewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    viewModel.getExpenses(true)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showModal(null, true, ModalType.CREATE) }) {
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
                retry = { viewModel.getExpenses(true) },
                showModal,
                showAlert
            )
        }
    }
}


