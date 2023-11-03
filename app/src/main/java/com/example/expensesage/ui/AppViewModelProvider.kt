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
                userSettings = expenseSageApplicaton().userSettings,
                currencyApiExecutor = expenseSageApplicaton().currencyExecutor,
            )
        }
        initializer {
            StatisticViewModel(expenseSageApplicaton().container.expenseRepository)
        }
        initializer {
            CurrencyViewModel(
                userPref = expenseSageApplicaton().userSettings,
                currencyApiExecutor = expenseSageApplicaton().currencyExecutor,
            )
        }
        initializer {
            MainViewModel()
        }
    }
}

fun CreationExtras.expenseSageApplicaton(): ExpenseSageApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ExpenseSageApplication)
