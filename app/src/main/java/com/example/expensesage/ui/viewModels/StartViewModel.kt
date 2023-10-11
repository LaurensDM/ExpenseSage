package com.example.expensesage.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.Expense
import com.example.expensesage.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class StartViewModel(expenseRepository: ExpenseRepository) :
    ViewModel() {
    val startUiState: StateFlow<StartUiState> =
        expenseRepository.getTop5Expenses().map { StartUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StartUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class StartUiState(val expenses: List<Expense> = listOf())