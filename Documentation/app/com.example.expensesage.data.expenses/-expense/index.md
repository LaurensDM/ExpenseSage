//[app](../../../index.md)/[com.example.expensesage.data.expenses](../index.md)/[Expense](index.md)

# Expense

[androidJvm]\
data class [Expense](index.md)(val id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, val date: [LocalDateTime](https://developer.android.com/reference/kotlin/java/time/LocalDateTime.html) = LocalDateTime.now(), @[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html)val imageResourceId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), val name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Unknown&quot;, val amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0, val owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), val category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Other&quot;)

Expense Entity

## Constructors

| | |
|---|---|
| [Expense](-expense.md) | [androidJvm]<br>constructor(id: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0, date: [LocalDateTime](https://developer.android.com/reference/kotlin/java/time/LocalDateTime.html) = LocalDateTime.now(), @[DrawableRes](https://developer.android.com/reference/kotlin/androidx/annotation/DrawableRes.html)imageResourceId: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Unknown&quot;, amount: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0, owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), category: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = &quot;Other&quot;) |

## Properties

| Name | Summary |
|---|---|
| [amount](amount.md) | [androidJvm]<br>val [amount](amount.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0<br>Amount of expense |
| [category](category.md) | [androidJvm]<br>val [category](category.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Category of expense |
| [date](date.md) | [androidJvm]<br>val [date](date.md): [LocalDateTime](https://developer.android.com/reference/kotlin/java/time/LocalDateTime.html)<br>LocalDateTime (auto-generated) |
| [id](id.md) | [androidJvm]<br>val [id](id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) = 0<br>Int (auto-generated) |
| [imageResourceId](image-resource-id.md) | [androidJvm]<br>val [imageResourceId](image-resource-id.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Drawable resource id |
| [name](name.md) | [androidJvm]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Name of expense |
| [owed](owed.md) | [androidJvm]<br>val [owed](owed.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Boolean (true if owed, false if not) |

## Functions

| Name | Summary |
|---|---|
| [toExpenseDetail](../../com.example.expensesage.ui.utils/to-expense-detail.md) | [androidJvm]<br>fun [Expense](index.md).[toExpenseDetail](../../com.example.expensesage.ui.utils/to-expense-detail.md)(currencyRate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [ExpenseDetail](../../com.example.expensesage.ui.utils/-expense-detail/index.md)<br>This function converts an expense to an expense detail. |
