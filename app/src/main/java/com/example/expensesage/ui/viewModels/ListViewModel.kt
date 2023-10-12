package com.example.expensesage.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.Expense
import com.example.expensesage.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.Month

class ListViewModel(val expenseRepository: ExpenseRepository) :
    ViewModel() {

    fun get5Expenses(): StateFlow<ListUiState> {
        return expenseRepository.getTop5Expenses().map { ListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ListUiState()
            );
    }

    fun getExpenses(owed: Boolean): StateFlow<MapUiState> {
        return expenseRepository.getExpenses(owed).map { MapUiState(groupExpenses(it)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = MapUiState()
            );
    }

    private fun groupExpenses(list: List<Expense>): Map<Pair<Month, Int>, List<Expense>> {
        val expensesList : List<Expense> = list
        return expensesList.groupBy { it.date.month to it.date.year }
    }



    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class MapUiState(val expenses: Map<Pair<Month, Int>, List<Expense>> = emptyMap())

data class ListUiState(val expenses: List<Expense> = emptyList())

