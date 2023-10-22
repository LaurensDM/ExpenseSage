package com.example.expensesage.ui.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.MainViewModel
import com.example.expensesage.ui.screens.CurrencyScreen
import com.example.expensesage.ui.screens.ExpenseScreen
import com.example.expensesage.ui.screens.OwedScreen
import com.example.expensesage.ui.screens.SettingScreen
import com.example.expensesage.ui.screens.StartScreen
import com.example.expensesage.ui.screens.SummaryScreen
import com.example.expensesage.ui.utils.Navigations
import com.example.expensesage.ui.viewModels.APIViewModel

/**
 * Composable that displays content based on the current route
 *
 * @param navController The navController that handles the navigation
 * @param viewModel The viewModel that holds the data
 */
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
            ExpenseScreen(
                viewModel = viewModel,
                onCreateClicked = {
                    navController.navigate("Edit/0") {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
            )
        }
        composable(Navigations.Owed.route) {
            OwedScreen(
                viewModel = viewModel,
//                onCreateClicked = { navController.navigate(Navigations.Currencies.route) }
            )
        }
        composable(Navigations.Currencies.route) {
            val apiViewModel: APIViewModel = viewModel(factory = AppViewModelProvider.Factory)
            CurrencyScreen(apiViewModel.currencyUiState, onRetry = { apiViewModel.getCurrencies() })
        }
        composable(Navigations.Summary.route) {
            SummaryScreen()
        }
        composable(Navigations.Settings.route) {
            SettingScreen(viewModel = viewModel)
        }
//        composable(Navigations.Edit.route) { navBackStackEntry ->
//            val id = navBackStackEntry.arguments?.getString("id") ?: ""
//            Edit(id = id, onDoneClicked = { navController.navigate(Navigations.Start.route) })
//        }
    }
}
