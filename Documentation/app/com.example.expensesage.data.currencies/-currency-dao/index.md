//[app](../../../index.md)/[com.example.expensesage.data.currencies](../index.md)/[CurrencyDao](index.md)

# CurrencyDao

[androidJvm]\
interface [CurrencyDao](index.md)

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(currency: [Currency](../-currency/index.md)) |
| [getAllCurrencies](get-all-currencies.md) | [androidJvm]<br>abstract suspend fun [getAllCurrencies](get-all-currencies.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../-currency/index.md)&gt;<br>Get all currencies |
| [getAllCurrenciesFlow](get-all-currencies-flow.md) | [androidJvm]<br>abstract fun [getAllCurrenciesFlow](get-all-currencies-flow.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../-currency/index.md)&gt;&gt;<br>Get all currencies in a flow |
| [getCurrency](get-currency.md) | [androidJvm]<br>abstract suspend fun [getCurrency](get-currency.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Currency](../-currency/index.md)<br>Get currency |
| [getCurrencyFlow](get-currency-flow.md) | [androidJvm]<br>abstract fun [getCurrencyFlow](get-currency-flow.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Currency](../-currency/index.md)&gt;<br>Get currency in a flow |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(currency: [Currency](../-currency/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract suspend fun [insertAll](insert-all.md)(currencies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../-currency/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(currency: [Currency](../-currency/index.md)) |
