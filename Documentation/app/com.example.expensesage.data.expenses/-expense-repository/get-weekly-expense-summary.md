//[app](../../../index.md)/[com.example.expensesage.data.expenses](../index.md)/[ExpenseRepository](index.md)/[getWeeklyExpenseSummary](get-weekly-expense-summary.md)

# getWeeklyExpenseSummary

[androidJvm]\
abstract fun [getWeeklyExpenseSummary](get-weekly-expense-summary.md)(yearMonth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../../com.example.expensesage.ui.utils/-expense-summary-item/index.md)&gt;&gt;

Get expenses for all weeks in a month

#### Return

Flow<List<ExpenseSummaryItem>> List of expenses for each week

#### Parameters

androidJvm

| | |
|---|---|
| yearMonth | String year and month |
