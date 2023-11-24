//[app](../../../index.md)/[com.example.expensesage.data.expenses](../index.md)/[ExpenseDao](index.md)

# ExpenseDao

[androidJvm]\
interface [ExpenseDao](index.md)

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(expense: [Expense](../-expense/index.md)) |
| [getAllExpenses](get-all-expenses.md) | [androidJvm]<br>abstract fun [getAllExpenses](get-all-expenses.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../-expense/index.md)&gt;&gt; |
| [getExpense](get-expense.md) | [androidJvm]<br>abstract fun [getExpense](get-expense.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[Expense](../-expense/index.md)&gt; |
| [getExpenses](get-expenses.md) | [androidJvm]<br>abstract fun [getExpenses](get-expenses.md)(owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../-expense/index.md)&gt;&gt; |
| [getMonthlyExpenseSummary](get-monthly-expense-summary.md) | [androidJvm]<br>abstract fun [getMonthlyExpenseSummary](get-monthly-expense-summary.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../../com.example.expensesage.ui.utils/-expense-summary-item/index.md)&gt;&gt; |
| [getSumOfAll](get-sum-of-all.md) | [androidJvm]<br>abstract fun [getSumOfAll](get-sum-of-all.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [getSumOfCategory](get-sum-of-category.md) | [androidJvm]<br>abstract fun [getSumOfCategory](get-sum-of-category.md)(searchQuery: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../../com.example.expensesage.ui.utils/-expense-summary-item/index.md)&gt;&gt; |
| [getSumOfCategoryAndDate](get-sum-of-category-and-date.md) | [androidJvm]<br>abstract fun [getSumOfCategoryAndDate](get-sum-of-category-and-date.md)(searchQuery: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [getSumOfDate](get-sum-of-date.md) | [androidJvm]<br>abstract fun [getSumOfDate](get-sum-of-date.md)(searchQuery: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [getSumOfMonth](get-sum-of-month.md) | [androidJvm]<br>abstract fun [getSumOfMonth](get-sum-of-month.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [getSumOfOwed](get-sum-of-owed.md) | [androidJvm]<br>abstract fun [getSumOfOwed](get-sum-of-owed.md)(owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [getSumOfWeek](get-sum-of-week.md) | [androidJvm]<br>abstract fun [getSumOfWeek](get-sum-of-week.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [getSumOfYear](get-sum-of-year.md) | [androidJvm]<br>abstract fun [getSumOfYear](get-sum-of-year.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [getTop5Expenses](get-top5-expenses.md) | [androidJvm]<br>abstract fun [getTop5Expenses](get-top5-expenses.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../-expense/index.md)&gt;&gt; |
| [getWeeklyExpensesForCurrentMonth](get-weekly-expenses-for-current-month.md) | [androidJvm]<br>abstract fun [getWeeklyExpensesForCurrentMonth](get-weekly-expenses-for-current-month.md)(yearMonth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../../com.example.expensesage.ui.utils/-expense-summary-item/index.md)&gt;&gt; |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(expense: [Expense](../-expense/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract suspend fun [insertAll](insert-all.md)(expenses: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../-expense/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(expense: [Expense](../-expense/index.md)) |
