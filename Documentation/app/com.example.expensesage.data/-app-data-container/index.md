//[app](../../../index.md)/[com.example.expensesage.data](../index.md)/[AppDataContainer](index.md)

# AppDataContainer

[androidJvm]\
class [AppDataContainer](index.md)(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), scope: CoroutineScope) : [AppContainer](../-app-container/index.md)

Class that handles the creation of the repositories

## Constructors

| | |
|---|---|
| [AppDataContainer](-app-data-container.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), scope: CoroutineScope) |

## Properties

| Name | Summary |
|---|---|
| [currencyRepository](currency-repository.md) | [androidJvm]<br>open override val [currencyRepository](currency-repository.md): [CurrencyRepository](../../com.example.expensesage.data.currencies/-currency-repository/index.md) |
| [expenseRepository](expense-repository.md) | [androidJvm]<br>open override val [expenseRepository](expense-repository.md): [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md) |
