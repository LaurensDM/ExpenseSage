package com.example.expensesage.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.data.expenses.Expense
import com.example.expensesage.data.expenses.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Month

class ListViewModel(val expenseRepository: ExpenseRepository) :
    ViewModel() {

    var mapUiState: MapUiState by mutableStateOf(MapUiState.Loading)
        private set

    var listUiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set

    fun get5Expenses(){
        viewModelScope.launch {
            listUiState = ListUiState.Loading
            listUiState = try {
                val expenses = expenseRepository.getTop5Expenses().first()
                ListUiState.Success(expenses)
            } catch (e: Exception) {
                ListUiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    fun getExpenses(owed: Boolean) {
        viewModelScope.launch {
            mapUiState = MapUiState.Loading
            mapUiState = try {
                val expenses = expenseRepository.getExpenses(owed).map { groupExpenses(it) }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = emptyMap(),
                )
                MapUiState.Success(expenses)
            } catch (e: Exception) {
                MapUiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    private fun groupExpenses(list: List<Expense>): Map<Pair<Month, Int>, List<Expense>> {
        val expensesList: List<Expense> = list
        return expensesList.groupBy { it.date.month to it.date.year }
    }
}

sealed interface MapUiState {
    data class Error(val message: String) : MapUiState
    data class Success(val expenses: StateFlow<Map<Pair<Month, Int>, List<Expense>>>) : MapUiState
    data object Loading : MapUiState

}

sealed interface ListUiState {
    data class Error(val message: String) : ListUiState
    data class Success(val expenses: List<Expense>) : ListUiState
    data object Loading : ListUiState
}
