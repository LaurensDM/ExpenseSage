package com.example.expensesage.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.expensesage.ExpenseSageApplication
import com.example.expensesage.ui.viewModels.ExpenseDetailsViewModel
import com.example.expensesage.ui.viewModels.ListViewModel
import com.example.expensesage.ui.viewModels.SettingsViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ExpenseDetailsViewModel(
                this.createSavedStateHandle(),
                expenseSageApplicaton().container.expenseRepository
            )
        }
        initializer {
            ListViewModel(expenseRepository = expenseSageApplicaton().container.expenseRepository)
        }
        initializer {
            SettingsViewModel(userSettings = expenseSageApplicaton().userSettings)
        }
    }
}

fun CreationExtras.expenseSageApplicaton(): ExpenseSageApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ExpenseSageApplication)