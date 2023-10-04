package com.example.expensesage.ui.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.screens.DueScreen
import com.example.expensesage.ui.screens.ExpenseScreen
import com.example.expensesage.ui.screens.OwedScreen
import com.example.expensesage.ui.screens.SettingScreen
import com.example.expensesage.ui.screens.StartScreen
import com.example.expensesage.ui.screens.SummaryScreen
import com.example.expensesage.ui.utils.Navigations

@Composable
fun NavBarGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Navigations.Start.route,
    ) {
        composable(Navigations.Start.route) {
            StartScreen(viewModel = viewModel)
        }
        composable(Navigations.Expenses.route) {
            ExpenseScreen(viewModel = viewModel)
        }
        composable(Navigations.Owed.route) {
            OwedScreen()
        }
        composable(Navigations.Due.route) {
            DueScreen()
        }
        composable(Navigations.Summary.route) {
            SummaryScreen()
        }
        composable(Navigations.Settings.route) {
            SettingScreen()
        }
    }
}


