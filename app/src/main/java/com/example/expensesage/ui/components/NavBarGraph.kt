package com.example.expensesage.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensesage.data.Expense
import com.example.expensesage.ui.screens.CurrencyScreen
import com.example.expensesage.ui.screens.ExpenseScreen
import com.example.expensesage.ui.screens.OwedScreen
import com.example.expensesage.ui.screens.SettingScreen
import com.example.expensesage.ui.screens.StartScreen
import com.example.expensesage.ui.screens.SummaryScreen
import com.example.expensesage.ui.utils.ModalType
import com.example.expensesage.ui.utils.Navigations
import com.example.expensesage.ui.viewModels.MainViewModel
import com.example.expensesage.ui.viewModels.SnackBarType

/**
 * Composable that displays content based on the current route
 *
 * @param navController The navController that handles the navigation
 * @param viewModel The viewModel that holds the data
 */
@Composable
fun NavBarGraph(
    navController: NavHostController,
    showModal: (expense: Expense?, isOwed: Boolean, modalType: ModalType) -> Unit,
    showAlert: (onConfirm: () -> Unit, title: String, onCancel: () -> Unit) -> Unit,
    showSnackbar: (message: String, snackBarType: SnackBarType) -> Unit,
    ) {
    NavHost(
        navController = navController,
        startDestination = Navigations.Start.route,
    ) {
        composable(Navigations.Start.route) {
            StartScreen()
        }
        composable(Navigations.Expenses.route) {
            ExpenseScreen(
                showModal = showModal,
                showAlert = showAlert,
            )
        }
        composable(Navigations.Owed.route) {
            OwedScreen(
                showModal = showModal,
                showAlert = showAlert,
            )
        }
        composable(Navigations.Currencies.route) {
            CurrencyScreen()
        }
        composable(Navigations.Summary.route) {
            SummaryScreen()
        }
        composable(Navigations.Settings.route) {
            SettingScreen(showSnackBar = showSnackbar)
        }
    }
}
