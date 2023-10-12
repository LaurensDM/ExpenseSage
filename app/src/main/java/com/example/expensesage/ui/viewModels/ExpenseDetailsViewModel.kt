package com.example.expensesage.ui.viewModels

import android.icu.text.NumberFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.data.ExpenseRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpenseDetailsViewModel(
    private val expenseRepository: ExpenseRepository,
) : ViewModel() {


    fun getExpense(id: Int): StateFlow<ExpenseDetailState> {
        val uiState: StateFlow<ExpenseDetailState> =
            expenseRepository.getExpense(id)
                .filterNotNull()
                .map {
                    ExpenseDetailState(expenseDetails = it.toExpenseDetails())
                }.stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                    initialValue = ExpenseDetailState()
                )
        return uiState;
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.delete(expense = expense)
        }
    }

     fun saveExpense(expense: ExpenseDetail) {
        viewModelScope.launch {
            if (validateInput(expense)) {
                expenseRepository.insert(expense = expense.toExpense())
            }
        }

    }

    fun updateExpense(expense: ExpenseDetail) {
        viewModelScope.launch {
            if (validateInput(expense)) {
                expenseRepository.update(expense = expense.toExpense())
            }
        }

    }

    private fun validateInput(expense: ExpenseDetail): Boolean {
        return true
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class ExpenseDetailState(
    val expenseDetails: ExpenseDetail = ExpenseDetail(),
    val isEntryValid: Boolean = false
)

data class ExpenseDetail(
    var id: Int = 0,
    var date: String = LocalDate.now().toString(),
    var expenseName: String = "Unknown",
    var expense: String = "0.00",
    var owed: Boolean = false,
)

fun Expense.toExpenseDetails(): ExpenseDetail = ExpenseDetail(
    id = id,
    expenseName = expenseName,
    expense = "$expense",
    owed = owed,
    date = date.toString()
)

fun ExpenseDetail.toExpense(): Expense = Expense(
    id = id,
    expenseName = expenseName,
    expense = expense.toDoubleOrNull() ?: 0.0,
    owed = owed,
    imageResourceId = if (owed) R.drawable.owed else R.drawable.cost,
    date = LocalDate.parse(date)
)

fun Expense.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(expense)
}