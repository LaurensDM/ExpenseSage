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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Month

/**
 * This class is responsible for the list view model.
 *
 * @property expenseRepository The expense repository
 */
class ListViewModel(val expenseRepository: ExpenseRepository) :
    ViewModel() {

    var mapUiState: MapUiState by mutableStateOf(MapUiState.Loading)
        private set

    var listUiState: ListUiState by mutableStateOf(ListUiState.Loading)
        private set

    /**
     * This function gets the top 5 expenses.
     *
     */
    fun get5Expenses(){
        viewModelScope.launch {
            listUiState = ListUiState.Loading
            listUiState = try {
                val expenses = expenseRepository.getTop5Expenses().map { it }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000),
                    initialValue = emptyList(),
                )
                ListUiState.Success(expenses)
            } catch (e: Exception) {
                ListUiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    /**
     * This function gets the expenses.
     *
     * @param owed  Whether the expense is owed
     */
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

    /**
     * This function groups expenses.
     *
     * @param list The list of expenses
     * @return The grouped expenses
     */
    private fun groupExpenses(list: List<Expense>): Map<Pair<Month, Int>, List<Expense>> {
        val expensesList: List<Expense> = list
        return expensesList.groupBy { it.date.month to it.date.year }
    }
}

/**
 * This class is responsible for the map UI state.
 *
 */
sealed interface MapUiState {
    data class Error(val message: String) : MapUiState
    data class Success(val expenses: StateFlow<Map<Pair<Month, Int>, List<Expense>>>) : MapUiState
    data object Loading : MapUiState

}

/**
 * This class is responsible for the list UI state.
 *
 */
sealed interface ListUiState {
    data class Error(val message: String) : ListUiState
    data class Success(val expenses: StateFlow<List<Expense>>) : ListUiState
    data object Loading : ListUiState
}
