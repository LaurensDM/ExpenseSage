//[app](../../index.md)/[com.example.expensesage.data.currencies](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [Currency](-currency/index.md) | [androidJvm]<br>data class [Currency](-currency/index.md)(val currencyCode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), val rate: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), val comparedCurrency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Currency Entity |
| [CurrencyDao](-currency-dao/index.md) | [androidJvm]<br>interface [CurrencyDao](-currency-dao/index.md) |
| [CurrencyRepository](-currency-repository/index.md) | [androidJvm]<br>interface [CurrencyRepository](-currency-repository/index.md) |
| [CurrencyRepositoryImpl](-currency-repository-impl/index.md) | [androidJvm]<br>class [CurrencyRepositoryImpl](-currency-repository-impl/index.md)(currencyDao: [CurrencyDao](-currency-dao/index.md), settings: [UserSettingsService](../com.example.expensesage.data/-user-settings-service/index.md), api: [CurrencyApiExecutor](../com.example.expensesage.network/-currency-api-executor/index.md)) : [CurrencyRepository](-currency-repository/index.md) |
