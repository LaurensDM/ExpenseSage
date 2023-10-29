package com.example.expensesage.ui.utils

class ExpenseSummary {
    var categoryData: List<ExpenseSummaryItem> = emptyList()
        private set
    var monthlyData: List<ExpenseSummaryItem> = emptyList()
        private set
    var totalSpent: Double = 0.0
        private set

    fun init(
        categoryData: List<ExpenseSummaryItem>,
        monthlyData: List<ExpenseSummaryItem>,
        totalSpent: Double = 0.0,
    ) {
        this.categoryData = categoryData
        this.monthlyData = monthlyData
        this.totalSpent = totalSpent
    }
}

data class ExpenseSummaryItem(
    val subject: String,
    val totalExpense: Double,
)
