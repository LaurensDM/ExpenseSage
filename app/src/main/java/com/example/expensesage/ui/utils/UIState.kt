package com.example.expensesage.ui.utils

import com.example.expensesage.R
import com.example.expensesage.data.Expense
import java.time.LocalDateTime


data class ExpenseDetail(
    var id: Int = 0,
    var date: String = LocalDateTime.now().toString(),
    var name: String = "Unknown",
    var amount: String = "0.00",
    var owed: Boolean = false,
    var category: String = "Other",
)

fun ExpenseDetail.toExpense(): Expense = Expense(
    id = id,
    name = name,
    amount = amount.toDoubleOrNull() ?: 0.0,
    owed = owed,
    imageResourceId = if (owed) R.drawable.owed else R.drawable.cost,
    date = LocalDateTime.parse(date),
    category = category,
)

fun Expense.toExpenseDetail(): ExpenseDetail = ExpenseDetail(
    id = id,
    name = name,
    amount = amount.toString(),
    owed = owed,
    date = date.toString(),
    category = category,
)

fun Number.formatToCurrency(): String {
    return String.format("%.2f", this)
}