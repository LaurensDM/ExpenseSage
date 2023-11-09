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
import com.example.expensesage.ui.utils.toExpenseDetail
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
        Log.i("ExpenseDetailsViewModel", "init")
        viewModelScope.launch {
            expenseDetailState = expenseDetailState.copy(
                amount = (expenseDetailState.amount.toDouble() * userPref.currencyModifier.first()).formatToCurrency()
            )
            format.isGroupingUsed = false
            format.maximumFractionDigits = 2
        }

    }

    fun resetState() {
        expenseDetailState = ExpenseDetail()
        nameError = false
        amountError = false
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            if (expense.owed) {
                val moneyOwed = userPref.moneyOwed.first()
                if (moneyOwed - expense.amount >= 0) {
                    userPref.saveMoneyOwed((moneyOwed - expense.amount))
                }
            } else {
                val moneyAvailable = userPref.moneyAvailable.first()
                userPref.saveMoneyAvailable(moneyAvailable + expense.amount)
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
            val moneyAvailable = userPref.moneyAvailable.first()
            userPref.saveMoneyAvailable(moneyAvailable - expense.amount)
            expenseRepository.update(expense = payedExpense)
        }
    }

    fun saveExpense() {
        viewModelScope.launch {
            Log.d("ExpenseDetailsViewModel", "SAVING EXPENSE")
            Log.d("ExpenseDetailsViewModel", "state: $expenseDetailState")
            if (validateInput()) {
                val newExpense = expenseDetailState.toExpense(userPref.currencyModifier.first())
                if (newExpense.owed) {
                    userPref.saveMoneyOwed(moneyOwed = (userPref.moneyOwed.first() + newExpense.amount))
                } else {
                    userPref.saveMoneyAvailable(moneyAvailable = (userPref.moneyAvailable.first() - newExpense.amount))
                }
                Log.d("ExpenseDetailsViewModel", "Saving newExpense: $newExpense")
                expenseRepository.insert(expense = newExpense)
            }
        }
    }

    fun updateExpense(originalExpense: Expense) {
        viewModelScope.launch {
            Log.d("ExpenseDetailsViewModel", "UPDATING EXPENSE")
            Log.d("ExpenseDetailsViewModel", "updateExpense: $expenseDetailState")
            Log.d("ExpenseDetailsViewModel", "original: $originalExpense")
            // This code should be mostly useless since the user shouldn't be able to change
            // the owed status of an expense through an edit. Could be a feature in the future
            if (validateInput()) {
                val newExpense = expenseDetailState.toExpense(userPref.currencyModifier.first())
                val moneyOwed = userPref.moneyOwed.first()
                if (originalExpense.owed) {
                    if (newExpense.owed) {
                        // If the expense was owed and still is, then we only need to update the amount
                        userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.amount + newExpense.amount))
                    } else {
                        // If the expense was owed and now is not, then we need to lower the money owed with the original amount
                        // and lower the moneyAvailable with the new amount
                        userPref.saveMoneyAvailable(moneyAvailable = (userPref.moneyAvailable.first() - newExpense.amount))
                        userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.amount))
                    }
                } else {
                    if (newExpense.owed) {
                        // If the expense was not owed and now is, then we need to raise the money owed with the new amount
                        // and raise the moneyAvailable with the original amount.
                        userPref.saveMoneyOwed(moneyOwed = (moneyOwed + newExpense.amount))
                        userPref.saveMoneyAvailable(moneyAvailable = (userPref.moneyAvailable.first() + originalExpense.amount))

                    } else {
                        // If the expense was not owed and still is not, then we only need to update the moneyAvailable
                        // The originalExpense gets 'refunded' and the newExpense gets 'charged'
                        userPref.saveMoneyAvailable(moneyAvailable = (userPref.moneyAvailable.first() + originalExpense.amount - newExpense.amount))
                    }
                }
                expenseRepository.update(expense = newExpense)
            }
        }
    }

    private fun validateInput(expense: ExpenseDetail = expenseDetailState): Boolean {
        try {
            nameError = expense.name.isBlank()
            val amount = expense.amount.replace(",", ".").toDouble()
            // If no error is thrown, then the amount is valid
            Log.i("ExpenseDetailsViewModel", "validateInput: amount is valid: $amount")
            if (amount <= 0) {
                throw Exception("Amount is negative")
            }
            amountError = false
        } catch (e: Exception) {
            Log.d("ExpenseDetailsViewModel", "validateInput: ${e.localizedMessage}")
            expense.amount = "0"
            amountError = true
        }

        return !(nameError || amountError)
    }

    fun updateState(expense: ExpenseDetail) {
        validateInput(expense)
        expenseDetailState = expense
    }

    fun initializeState(expense: Expense) {
        viewModelScope.launch {
            expenseDetailState = expense.toExpenseDetail(userPref.currencyModifier.first())
            nameError = false
            amountError = false
        }
    }
}
