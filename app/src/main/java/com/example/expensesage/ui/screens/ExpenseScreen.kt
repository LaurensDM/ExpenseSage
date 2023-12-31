package com.example.expensesage.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.ExpenseFloatingActionButton
import com.example.expensesage.ui.components.ExpenseList
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.viewModels.ListViewModel
import com.example.expensesage.ui.viewModels.MapUiState

/**
 * This function is responsible for the expense screen.
 *
 * @param showModal The show modal function
 * @param showAlert The show alert function
 * @param viewModel The list view model
 */
@Composable
fun ExpenseScreen(
    showModal: (expense: Expense?, isOwed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: Int, subject: String, onCancel: () -> Unit) -> Unit,
    viewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    LaunchedEffect(viewModel ){
        viewModel.getExpenses(false)
    }


    Scaffold(
        floatingActionButton = {
            ExpenseFloatingActionButton(
                onClick = { showModal(null, false, ModalType.CREATE) }
            )
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

/**
 * This function is responsible for the expenses view.
 *
 * @param mapUiState The map ui state
 * @param retry The retry function
 * @param showModal The show modal function
 * @param showAlert The show alert function
 */
@Composable
fun ExpensesView(
    mapUiState: MapUiState,
    retry: () -> Unit = {},
    showModal: (expense: Expense?, isOwed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: Int, subject: String, onCancel: () -> Unit) -> Unit,
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

