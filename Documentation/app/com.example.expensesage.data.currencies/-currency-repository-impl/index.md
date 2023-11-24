//[app](../../../index.md)/[com.example.expensesage.data.currencies](../index.md)/[CurrencyRepositoryImpl](index.md)

# CurrencyRepositoryImpl

[androidJvm]\
class [CurrencyRepositoryImpl](index.md)(currencyDao: [CurrencyDao](../-currency-dao/index.md), settings: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), api: [CurrencyApiExecutor](../../com.example.expensesage.network/-currency-api-executor/index.md)) : [CurrencyRepository](../-currency-repository/index.md)

## Constructors

| | |
|---|---|
| [CurrencyRepositoryImpl](-currency-repository-impl.md) | [androidJvm]<br>constructor(currencyDao: [CurrencyDao](../-currency-dao/index.md), settings: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), api: [CurrencyApiExecutor](../../com.example.expensesage.network/-currency-api-executor/index.md)) |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [androidJvm]<br>open suspend override fun [delete](delete.md)(currency: [Currency](../-currency/index.md)) |
| [getAllCurrencies](get-all-currencies.md) | [androidJvm]<br>open suspend override fun [getAllCurrencies](get-all-currencies.md)(): Flow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../-currency/index.md)&gt;&gt;<br>Get all currencies |
| [getCurrency](get-currency.md) | [androidJvm]<br>open suspend override fun [getCurrency](get-currency.md)(id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): Flow&lt;[Currency](../-currency/index.md)&gt;<br>Get currency by id |
| [insert](insert.md) | [androidJvm]<br>open suspend override fun [insert](insert.md)(currency: [Currency](../-currency/index.md)) |
| [insertAll](insert-all.md) | [androidJvm]<br>open suspend override fun [insertAll](insert-all.md)(currencies: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../-currency/index.md)&gt;) |
| [update](update.md) | [androidJvm]<br>open suspend override fun [update](update.md)(currency: [Currency](../-currency/index.md)) |
