package com.example.expensesage.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.expensesage.ExpenseSageApplication
import com.example.expensesage.ui.viewModels.CurrencyViewModel
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import com.example.expensesage.ui.viewModels.ListViewModel
import com.example.expensesage.ui.viewModels.MainViewModel
import com.example.expensesage.ui.viewModels.SettingsViewModel
import com.example.expensesage.ui.viewModels.StatisticViewModel
import com.example.expensesage.workers.changeInterval
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * This object is responsible for providing the view models.
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ExpenseDetailsViewModel(
                expenseSageApplicaton().userSettings,
                expenseSageApplicaton().container.expenseRepository,
            )
        }
        initializer {
            ListViewModel(expenseRepository = expenseSageApplicaton().container.expenseRepository)
        }
        initializer {
            SettingsViewModel(
                changeInterval = {
                    CoroutineScope(Dispatchers.IO).launch {
                        changeInterval(expenseSageApplicaton().applicationContext,
                            expenseSageApplicaton().userSettings)
                    }
                },
                userSettings = expenseSageApplicaton().userSettings,
                expenseRepository = expenseSageApplicaton().container.expenseRepository,
                currencyRepository = expenseSageApplicaton().container.currencyRepository,
            )
        }
        initializer {
            StatisticViewModel(
                expenseSageApplicaton().container.expenseRepository,
                expenseSageApplicaton().userSettings
            )
        }
        initializer {
            CurrencyViewModel(
                userPref = expenseSageApplicaton().userSettings,
                currencyRepository = expenseSageApplicaton().container.currencyRepository,
            )
        }
        initializer {
            MainViewModel()
        }
    }
}

/**
 * This function returns the application.
 *
 * @return The application
 */
fun CreationExtras.expenseSageApplicaton(): ExpenseSageApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ExpenseSageApplication)
