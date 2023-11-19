package com.example.expensesage.ui.utils

import com.example.expensesage.R
import com.example.expensesage.data.expenses.Expense
import java.time.LocalDateTime

/**
 * This class is responsible for the expense detail.
 *
 * @property id The id
 * @property date The date
 * @property name The name
 * @property amount The amount
 * @property owed The owed
 * @property category The category
 */
data class ExpenseDetail(
    var id: Int = 0,
    var date: String = LocalDateTime.now().toString(),
    var name: String = "Unknown",
    var amount: String = "1",
    var owed: Boolean = false,
    var category: String = "Other",
)

/**
 * This function converts an expense detail to an expense.
 *
 * @param currencyRate The currency rate
 * @return The expense
 */
fun ExpenseDetail.toExpense(currencyRate: Double): Expense = Expense(
    id = id,
    name = name,
    amount = (amount.formatToDouble() / currencyRate),
    owed = owed,
    imageResourceId = if (owed) R.drawable.owed else R.drawable.cost,
    date = LocalDateTime.parse(date),
    category = category,
)

/**
 * This function converts an expense to an expense detail.
 *
 * @param currencyRate The currency rate
 * @return The expense detail
 */
fun Expense.toExpenseDetail(currencyRate: Double): ExpenseDetail = ExpenseDetail(
    id = id,
    name = name,
    amount = (amount * currencyRate).formatToCurrency(),
    owed = owed,
    date = date.toString(),
    category = category,
)

/**
 * This function formats a number to a currency string.
 *
 * @return String: The formatted number
 */
fun Number.formatToCurrency(): String {
    return String.format("%.2f", this)
}

/**
 * This function formats a string to a double.
 *
 * @return Double: a double value
 */
fun String.formatToDouble(): Double {
    return this.replace(",", ".").toDouble()
}