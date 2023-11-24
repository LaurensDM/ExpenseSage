//[app](../../../index.md)/[com.example.expensesage.ui.utils](../index.md)/[ExpenseDetail](index.md)

# ExpenseDetail

[androidJvm]\
data class [ExpenseDetail](index.md)(var id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, var date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = LocalDateTime.now().toString(), var name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Unknown&quot;, var amount: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;1&quot;, var owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, var category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Other&quot;)

This class is responsible for the expense detail.

## Constructors

| | |
|---|---|
| [ExpenseDetail](-expense-detail.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = LocalDateTime.now().toString(), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Unknown&quot;, amount: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;1&quot;, owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Other&quot;) |

## Properties

| Name | Summary |
|---|---|
| [amount](amount.md) | [androidJvm]<br>var [amount](amount.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The amount |
| [category](category.md) | [androidJvm]<br>var [category](category.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The category |
| [date](date.md) | [androidJvm]<br>var [date](date.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The date |
| [id](id.md) | [androidJvm]<br>var [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>The id |
| [name](name.md) | [androidJvm]<br>var [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name |
| [owed](owed.md) | [androidJvm]<br>var [owed](owed.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>The owed |

## Functions

| Name | Summary |
|---|---|
| [toExpense](../to-expense.md) | [androidJvm]<br>fun [ExpenseDetail](index.md).[toExpense](../to-expense.md)(currencyRate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Expense](../../com.example.expensesage.data.expenses/-expense/index.md)<br>This function converts an expense detail to an expense. |
