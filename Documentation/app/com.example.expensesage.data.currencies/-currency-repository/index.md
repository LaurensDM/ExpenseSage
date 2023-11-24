//[app](../../../index.md)/[com.example.expensesage.data.currencies](../index.md)/[CurrencyRepository](index.md)

# CurrencyRepository

interface [CurrencyRepository](index.md)

#### Inheritors

| |
|---|
| [CurrencyRepositoryImpl](../-currency-repository-impl/index.md) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>abstract suspend fun [delete](delete.md)(currency: [Currency](../-currency/index.md)) |
| [getAllCurrencies](get-all-currencies.md) | [androidJvm]<br>abstract suspend fun [getAllCurrencies](get-all-currencies.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../-currency/index.md)&gt;&gt;<br>Get all currencies |
| [getCurrency](get-currency.md) | [androidJvm]<br>abstract suspend fun [getCurrency](get-currency.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Currency](../-currency/index.md)&gt;<br>Get currency by id |
| [insert](insert.md) | [androidJvm]<br>abstract suspend fun [insert](insert.md)(currency: [Currency](../-currency/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>abstract suspend fun [insertAll](insert-all.md)(currencies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../-currency/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>abstract suspend fun [update](update.md)(currency: [Currency](../-currency/index.md)) |
