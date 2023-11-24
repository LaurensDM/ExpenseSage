//[app](../../index.md)/[com.example.expensesage.data.expenses](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Expense](-expense/index.md) | [androidJvm]<br>data class [Expense](-expense/index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val date: [LocalDateTime](https://developer.android.com/reference/kotlin/java/time/LocalDateTime.html) = LocalDateTime.now(), @[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html)val imageResourceId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Unknown&quot;, val amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0, val owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Other&quot;)<br>Expense Entity |
| [ExpenseDao](-expense-dao/index.md) | [androidJvm]<br>interface [ExpenseDao](-expense-dao/index.md) |
| [ExpenseRepository](-expense-repository/index.md) | [androidJvm]<br>interface [ExpenseRepository](-expense-repository/index.md) |
| [OfflineExpenseRepository](-offline-expense-repository/index.md) | [androidJvm]<br>class [OfflineExpenseRepository](-offline-expense-repository/index.md)(expenseDao: [ExpenseDao](-expense-dao/index.md)) : [ExpenseRepository](-expense-repository/index.md) |

## Properties

| Name | Summary |
|---|---|
| [categories](categories.md) | [androidJvm]<br>val [categories](categories.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [expenses](expenses.md) | [androidJvm]<br>val [expenses](expenses.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](-expense/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [populateData](populate-data.md) | [androidJvm]<br>fun [populateData](populate-data.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](-expense/index.md)&gt;<br>Populate data for testing |
