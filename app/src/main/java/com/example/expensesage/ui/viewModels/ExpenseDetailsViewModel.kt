package com.example.expensesage.ui.viewModels

import android.icu.text.NumberFormat
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.data.ExpenseRepository
import com.example.expensesage.data.UserSettings
import com.example.expensesage.ui.utils.ExpenseDetail
import com.example.expensesage.ui.utils.formatToCurrency
import com.example.expensesage.ui.utils.toExpense
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Locale

class ExpenseDetailsViewModel(
    private val userPref: UserSettings,
    private val expenseRepository: ExpenseRepository,
) : ViewModel() {

    var expenseDetailState by mutableStateOf(ExpenseDetail())
        private set

    var nameError by mutableStateOf(false)
        private set

    var amountError by mutableStateOf(false)
        private set

    private val format: NumberFormat = NumberFormat.getInstance(Locale.getDefault())

    init {
        format.isGroupingUsed = false
        format.maximumFractionDigits = 2
    }
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            val moneyOwed = userPref.moneyOwed.first()
            if (expense.owed && moneyOwed - expense.amount >= 0) {
                userPref.saveMoneyOwed((moneyOwed - expense.amount))
            }
            expenseRepository.delete(expense = expense)
        }
    }

    fun payOwed(expense: Expense) {
        val payedExpense = expense.copy(
            owed = false,
            imageResourceId = R.drawable.cost,
            date = LocalDateTime.now(),
        )
        viewModelScope.launch {
            expenseRepository.update(expense = payedExpense)
        }
    }

    fun saveExpense() {
        viewModelScope.launch {
            Log.d("ExpenseDetailsViewModel", "SAVING EXPENSE")
            Log.d("ExpenseDetailsViewModel", "state: $expenseDetailState")
            val newExpense = expenseDetailState.toExpense()
            if (newExpense.owed) {
                userPref.saveMoneyOwed(moneyOwed = (userPref.moneyOwed.first() + newExpense.amount))
            }
            if (validateInput()) {
                expenseRepository.insert(expense = newExpense)
            }
        }
    }

    fun updateExpense(originalExpense: Expense) {
        viewModelScope.launch {
            Log.d("ExpenseDetailsViewModel", "UPDATING EXPENSE")
            Log.d("ExpenseDetailsViewModel", "updateExpense: $expenseDetailState")
            Log.d("ExpenseDetailsViewModel", "original: $originalExpense")
            val newExpense = expenseDetailState.toExpense()
            val moneyOwed = userPref.moneyOwed.first()
            if (originalExpense.owed) {
                if (newExpense.owed) {
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.amount + newExpense.amount))
                } else {
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.amount))
                }
            } else {
                if (newExpense.owed) {
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed + newExpense.amount))
                }
            }
            if (validateInput()) {
            expenseRepository.update(expense = newExpense)
            }
        }
    }

    private fun validateInput(expense: ExpenseDetail = expenseDetailState): Boolean {
        try {
            nameError = expense.name.isBlank()
            expense.amount.toDouble()
            // If no error is thrown, then the amount is valid
            amountError = false
        } catch (e: Exception) {
            Log.d("ExpenseDetailsViewModel", "validateInput: ${e.localizedMessage}")
            expense.amount = "0.00"
            amountError = true
        }

        return !(nameError || amountError)
    }

    fun updateState(expense: ExpenseDetail) {
        validateInput(expense)
        expenseDetailState = expense
    }
}
