package com.example.expensesage.ui.utils

/**
 *  This class is responsible for the expense summary.
 *
 * @property categoryData The category data
 * @property monthlyData The monthly data
 * @property totalSpent     The total spent
 * @property owedTotal The owed total
 * @property moneySaved The money saved
 */
data class ExpenseSummary(
    val categoryData: List<ExpenseSummaryItem> = emptyList(),
    val monthlyData: List<ExpenseSummaryItem> = emptyList(),
    val totalSpent: Double = 0.0,
    val owedTotal: Double = 0.0,
    val moneySaved: List<ExpenseSummaryItem> = emptyList()
)

/**
 * This class is responsible for an expense summary item.
 *
 * @property subject The subject
 * @property totalExpense The total expense
 */
data class ExpenseSummaryItem(
    val subject: String,
    val totalExpense: Double,
)
