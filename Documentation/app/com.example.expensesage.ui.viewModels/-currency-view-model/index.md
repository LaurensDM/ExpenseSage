//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[CurrencyViewModel](index.md)

# CurrencyViewModel

[androidJvm]\
class [CurrencyViewModel](index.md)(userPref: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), currencyRepository: [CurrencyRepository](../../com.example.expensesage.data.currencies/-currency-repository/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

This class is responsible for the currency view model.

## Constructors

| | |
|---|---|
| [CurrencyViewModel](-currency-view-model.md) | [androidJvm]<br>constructor(userPref: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), currencyRepository: [CurrencyRepository](../../com.example.expensesage.data.currencies/-currency-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [currency](currency.md) | [androidJvm]<br>var [currency](currency.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [currencyUIState](currency-u-i-state.md) | [androidJvm]<br>var [currencyUIState](currency-u-i-state.md): [CurrencyUIState](../-currency-u-i-state/index.md) |
| [list](list.md) | [androidJvm]<br>var [list](list.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Currency](../../com.example.expensesage.data.currencies/-currency/index.md)&gt; |
| [queryState](query-state.md) | [androidJvm]<br>var [queryState](query-state.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [getData](get-data.md) | [androidJvm]<br>fun [getData](get-data.md)()<br>This function gets the data. |
| [search](search.md) | [androidJvm]<br>fun [search](search.md)()<br>This function searches for a currency. |
| [updateQuery](update-query.md) | [androidJvm]<br>fun [updateQuery](update-query.md)(searchQuery: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>This function updates the query. |
