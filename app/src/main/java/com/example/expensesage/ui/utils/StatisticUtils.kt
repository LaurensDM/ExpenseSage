package com.example.expensesage.ui.utils

class ExpenseSummary {
    var donutChartData: List<ExpenseSummaryItem> = emptyList()
        private set
    var monthlyData: List<ExpenseSummaryItem> = emptyList()
        private set
    var owedComparison: Double = 0.0
        private set

    var totalSpent: Double = 0.0
        private set

    fun init(
        donutChartData: List<ExpenseSummaryItem>,
        monthlyData: List<ExpenseSummaryItem>,
        owedComparison: Double = 0.0,
        totalSpent: Double = 0.0,
    ) {
        this.donutChartData = donutChartData
        this.monthlyData = monthlyData
        this.owedComparison = owedComparison
        this.totalSpent = totalSpent
    }
}

data class ExpenseSummaryItem(
    val subject: String,
    val totalExpense: Double,
)
