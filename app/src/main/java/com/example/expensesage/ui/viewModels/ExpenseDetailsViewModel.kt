package com.example.expensesage.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesage.R
import com.example.expensesage.data.Expense
import com.example.expensesage.data.ExpenseRepository
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ExpenseDetailsViewModel(
    private val userPref: UserSettings,
    private val expenseRepository: ExpenseRepository,
) : ViewModel() {
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
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
            userPref.saveMoneySpent(payedExpense.amount)
            expenseRepository.update(expense = payedExpense)
        }
    }

    fun saveExpense(expense: ExpenseDetail) {
        viewModelScope.launch {
            if (expense.owed) {
                userPref.saveMoneyOwed(moneyOwed = (userPref.moneyOwed.first() + expense.amount.toDouble()))
            } else {
                userPref.saveMoneySpent(moneySpent = (userPref.moneySpent.first() + expense.amount.toDouble()))
            }
            if (validateInput()) {
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
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.amount + newExpense.amount))
                } else {
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed - originalExpense.amount))
                    userPref.saveMoneySpent(moneySpent = (moneySpent) + newExpense.amount)
                }
            } else {
                if (expense.owed) {
                    userPref.saveMoneySpent(moneySpent = (moneySpent - originalExpense.amount))
                    userPref.saveMoneyOwed(moneyOwed = (moneyOwed + newExpense.amount))
                } else {
                    userPref.saveMoneySpent(moneySpent = (moneySpent - originalExpense.amount + newExpense.amount))
                }
            }
//            if (validateInput(expense)) {
            expenseRepository.update(expense = newExpense)
//            }
        }
    }

    private fun validateInput(): Boolean {
        return true
    }

    data class ExpenseDetail(
        var id: Int = 0,
        var date: String = LocalDateTime.now().toString(),
        var name: String = "Unknown",
        var amount: String = "0.00",
        var owed: Boolean = false,
        var category: String = "Other",
    )

    private fun ExpenseDetail.toExpense(): Expense = Expense(
        id = id,
        name = name,
        amount = amount.toDoubleOrNull() ?: 0.0,
        owed = owed,
        imageResourceId = if (owed) R.drawable.owed else R.drawable.cost,
        date = LocalDateTime.parse(date),
        category = category,
    )
}
