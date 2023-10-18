package com.example.expensesage.ui.viewModels

import android.icu.text.NumberFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.data.ExpenseRepository
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpenseDetailsViewModel(
    private val userPref: UserSettings,
    private val expenseRepository: ExpenseRepository,
) : ViewModel() {
    var expenseUIState by mutableStateOf(ExpenseUIState())
        private set

    init {
        viewModelScope.launch {
            expenseUIState = expenseRepository.getExpense(1)
                .filterNotNull()
                .first()
                .toExpenseUIState(true)
        }
    }


//    fun getExpense(id: Int): StateFlow<ExpenseUIState> {
//        println(savedStateHandle.keys())
//        val uiState: StateFlow<ExpenseUIState> =
//            expenseRepository.getExpense(id)
//                .filterNotNull()
//                .map {
//                    ExpenseUIState(expenseDetails = it.toExpenseDetails())
//                }.stateIn(
//                    scope = viewModelScope,
//                    started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
//                    initialValue = ExpenseUIState()
//                )
//        expenseUIState = uiState.value
//        return uiState;
//    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            expenseRepository.delete(expense = expense)
        }
    }

    fun payOwed(expense: Expense) {

        val payedExpense = expense.copy(owed = false, imageResourceId = R.drawable.cost)

        viewModelScope.launch {
            expenseRepository.update(expense = payedExpense)
        }
    }

    fun saveExpense(expense: ExpenseDetail) {
        viewModelScope.launch {
            if (expense.owed) {
                userPref.saveMoneyOwed(moneyOwed = (userPref.moneyOwed.first() + expense.expense.toDouble()))

            } else {
                userPref.saveMoneySpent(moneySpent = (userPref.moneySpent.first() + expense.expense.toDouble()))
            }
            if (validateInput(expense)) {
                expenseRepository.insert(expense = expense.toExpense())
            }
        }

    }

    fun updateExpense(expense: ExpenseDetail, originalExpense: Expense) {
        viewModelScope.launch {
            val newExpense = expense.toExpense()
            val moneyOwed = userPref.moneyOwed.first()
            val moneySpent = userPref.moneySpent.first()
            if (originalExpense.owed) {
                if (expense.owed) {
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.expense + newExpense.expense))
                } else {
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.expense))
                    userPref.saveMoneySpent(moneySpent = (moneySpent) + newExpense.expense)
                }
            } else {
                if (expense.owed) {
                    userPref.saveMoneySpent(moneySpent = (moneySpent - originalExpense.expense))
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed + newExpense.expense))
                } else {
                    userPref.saveMoneySpent(moneySpent = (moneySpent - originalExpense.expense + newExpense.expense))
                }
            }
//            if (validateInput(expense)) {
            expenseRepository.update(expense = newExpense)
//            }
        }

    }

    fun updateUIState(expense: ExpenseDetail) {
        expenseUIState = ExpenseUIState(
            expenseDetails = expense,
            isEntryValid = validateInput(expense)
        )
    }

    private fun validateInput(expense: ExpenseDetail): Boolean {
        return true
    }


    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

}

data class ExpenseUIState(
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

fun Expense.toExpenseUIState(isEntryValid: Boolean): ExpenseUIState {
    return ExpenseUIState(
        expenseDetails = this.toExpenseDetails(),
        isEntryValid = isEntryValid
    )
}

fun Expense.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(expense)
}