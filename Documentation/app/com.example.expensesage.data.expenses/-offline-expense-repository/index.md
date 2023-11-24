//[app](../../../index.md)/[com.example.expensesage.data.expenses](../index.md)/[OfflineExpenseRepository](index.md)

# OfflineExpenseRepository

[androidJvm]\
class [OfflineExpenseRepository](index.md)(expenseDao: [ExpenseDao](../-expense-dao/index.md)) : [ExpenseRepository](../-expense-repository/index.md)

## Constructors

| | |
|---|---|
| [OfflineExpenseRepository](-offline-expense-repository.md) | [androidJvm]<br>constructor(expenseDao: [ExpenseDao](../-expense-dao/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>open suspend override fun [delete](delete.md)(expense: [Expense](../-expense/index.md)) |
| [getAllExpenses](get-all-expenses.md) | [androidJvm]<br>open override fun [getAllExpenses](get-all-expenses.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../-expense/index.md)&gt;&gt;<br>Get all expenses |
| [getExpense](get-expense.md) | [androidJvm]<br>open override fun [getExpense](get-expense.md)(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)): Flow&lt;[Expense](../-expense/index.md)&gt;<br>Get expense by id |
| [getExpenses](get-expenses.md) | [androidJvm]<br>open override fun [getExpenses](get-expenses.md)(owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../-expense/index.md)&gt;&gt;<br>Get all expenses that are owed or not owed |
| [getMonthlyExpenseSummary](get-monthly-expense-summary.md) | [androidJvm]<br>open override fun [getMonthlyExpenseSummary](get-monthly-expense-summary.md)(year: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../../com.example.expensesage.ui.utils/-expense-summary-item/index.md)&gt;&gt;<br>Get expenses for all months in a year |
| [getSumOfAll](get-sum-of-all.md) | [androidJvm]<br>open override fun [getSumOfAll](get-sum-of-all.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>Get sum of all expenses |
| [getSumOfCategory](get-sum-of-category.md) | [androidJvm]<br>open override fun [getSumOfCategory](get-sum-of-category.md)(searchQuery: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../../com.example.expensesage.ui.utils/-expense-summary-item/index.md)&gt;&gt;<br>Get sum of expenses by category |
| [getSumOfCategoryAndDate](get-sum-of-category-and-date.md) | [androidJvm]<br>open override fun [getSumOfCategoryAndDate](get-sum-of-category-and-date.md)(searchQuery: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>Get sum of expenses of a certain category and date |
| [getSumOfDate](get-sum-of-date.md) | [androidJvm]<br>open override fun [getSumOfDate](get-sum-of-date.md)(searchQuery: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>Get sum of expenses of a certain date |
| [getSumOfMonth](get-sum-of-month.md) | [androidJvm]<br>open override fun [getSumOfMonth](get-sum-of-month.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>Get sum of expenses for current month |
| [getSumOfOwed](get-sum-of-owed.md) | [androidJvm]<br>open override fun [getSumOfOwed](get-sum-of-owed.md)(owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>Get sum of expenses that are owed or not owed |
| [getSumOfWeek](get-sum-of-week.md) | [androidJvm]<br>open override fun [getSumOfWeek](get-sum-of-week.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>Get sum of expenses for current week |
| [getSumOfYear](get-sum-of-year.md) | [androidJvm]<br>open override fun [getSumOfYear](get-sum-of-year.md)(): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>Get sum of expenses for current year |
| [getTop5Expenses](get-top5-expenses.md) | [androidJvm]<br>open override fun [getTop5Expenses](get-top5-expenses.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../-expense/index.md)&gt;&gt;<br>Get 5 most recent expenses |
| [getWeeklyExpenseSummary](get-weekly-expense-summary.md) | [androidJvm]<br>open override fun [getWeeklyExpenseSummary](get-weekly-expense-summary.md)(yearMonth: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../../com.example.expensesage.ui.utils/-expense-summary-item/index.md)&gt;&gt;<br>Get expenses for all weeks in a month |
| [insert](insert.md) | [androidJvm]<br>open suspend override fun [insert](insert.md)(expense: [Expense](../-expense/index.md)) |
| [update](update.md) | [androidJvm]<br>open suspend override fun [update](update.md)(expense: [Expense](../-expense/index.md)) |
