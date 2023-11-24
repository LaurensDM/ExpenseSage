//[app](../../../index.md)/[com.example.expensesage.network](../index.md)/[CurrencyApiExecutor](index.md)

# CurrencyApiExecutor

[androidJvm]\
class [CurrencyApiExecutor](index.md)(userSettings: [UserSettings](../../com.example.expensesage.data/-user-settings/index.md))

Currency API Executor

## Constructors

| | |
|---|---|
| [CurrencyApiExecutor](-currency-api-executor.md) | [androidJvm]<br>constructor(userSettings: [UserSettings](../../com.example.expensesage.data/-user-settings/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [getCurrencyRates](get-currency-rates.md) | [androidJvm]<br>suspend fun [getCurrencyRates](get-currency-rates.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../../com.example.expensesage.data.currencies/-currency/index.md)&gt;<br>Get currency rates |
| [getRate](get-rate.md) | [androidJvm]<br>suspend fun [getRate](get-rate.md)(currency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Get rate for currency (e.g. USD) |
