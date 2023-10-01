package com.example.expensesage.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensesage.ui.DueScreen
import com.example.expensesage.ui.ExpenseScreen
import com.example.expensesage.ui.OwedScreen
import com.example.expensesage.ui.StartScreen
import com.example.expensesage.ui.SummaryScreen

@Composable
fun NavBarGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Navigations.Start.route,
    ) {
        composable(Navigations.Start.route) {
            StartScreen()
        }
        composable(Navigations.Expenses.route) {
            ExpenseScreen()
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
    }
}


