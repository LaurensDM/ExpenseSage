package com.example.expensesage.ui.utils

data class ExpenseSummary(
    val categoryData: List<ExpenseSummaryItem> = emptyList(),
    val monthlyData: List<ExpenseSummaryItem> = emptyList(),
    val totalSpent: Double = 0.0,
    val owedTotal: Double = 0.0,
    val moneySaved: List<ExpenseSummaryItem> = emptyList()
)

data class ExpenseSummaryItem(
    val subject: String,
    val totalExpense: Double,
)
