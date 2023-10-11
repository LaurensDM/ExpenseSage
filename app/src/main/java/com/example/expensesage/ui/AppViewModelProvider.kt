package com.example.expensesage.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.expensesage.ExpenseSageApplicaton
import com.example.expensesage.ui.viewModels.StartViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            StartViewModel(expenseRepository = expenseSageApplicaton().container.expenseRepository)
        }
    }
}

fun CreationExtras.expenseSageApplicaton(): ExpenseSageApplicaton =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ExpenseSageApplicaton)