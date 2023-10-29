package com.example.expensesage.ui.screens

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
import com.example.expensesage.ui.components.ExpenseList
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.viewModels.ListViewModel
import com.example.expensesage.ui.viewModels.MainViewModel

/**
 * Composable that displays the expense screen of the app
 *
 * @param viewModel The view model that is used to store the state of the app
 */
@Composable
fun ExpenseScreen(
    viewModel: MainViewModel,
    dataViewModel: ListViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val uiState by dataViewModel.getExpenses(false).collectAsState()
//    val apiKey = BuildConfig.FILE_API_KEY
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.showModal(modalType = ModalType.CREATE) }) {
                Icon(Icons.Default.Add, contentDescription = "Add expense")
            }
        },
//        floatingActionButtonPosition = FabPosition.End,
    ) { it ->
        ExpenseList(it = it, groupedExpenses = uiState.expenses, viewModel = viewModel)
    }
}

/**
 * Composable that displays what the UI of the app looks like in the design tab.
 */
// @Preview(showSystemUi = true)
// @Composable
// fun ExpenseScreenPreview() {
//    ExpenseSageTheme(darkTheme = false) {
//        ExpenseScreen(viewModel = MainViewModel(), onCreateClicked = {})
//    }
// }
//
// /**
// * Composable that displays what the UI of the app looks like in dark theme in the design tab.
// */
// @Preview
// @Composable
// fun ExpenseScreenDarkThemePreview() {
//    ExpenseSageTheme(darkTheme = true) {
//        ExpenseScreen(viewModel = MainViewModel(), onCreateClicked = {})
//    }
// }
