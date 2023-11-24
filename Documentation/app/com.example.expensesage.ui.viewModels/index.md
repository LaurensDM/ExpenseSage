//[app](../../index.md)/[com.example.expensesage.ui.viewModels](index.md)

# Package-level declarations

## Types

| Name | Summary |
|---|---|
| [CurrencyUIState](-currency-u-i-state/index.md) | [androidJvm]<br>interface [CurrencyUIState](-currency-u-i-state/index.md)<br>This interface is responsible for the currency UI state. |
| [CurrencyViewModel](-currency-view-model/index.md) | [androidJvm]<br>class [CurrencyViewModel](-currency-view-model/index.md)(userPref: [UserSettingsService](../com.example.expensesage.data/-user-settings-service/index.md), currencyRepository: [CurrencyRepository](../com.example.expensesage.data.currencies/-currency-repository/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)<br>This class is responsible for the currency view model. |
| [ExpenseDetailsViewModel](-expense-details-view-model/index.md) | [androidJvm]<br>class [ExpenseDetailsViewModel](-expense-details-view-model/index.md)(userPref: [UserSettingsService](../com.example.expensesage.data/-user-settings-service/index.md), expenseRepository: [ExpenseRepository](../com.example.expensesage.data.expenses/-expense-repository/index.md), format: [NumberFormat](https://developer.android.com/reference/kotlin/android/icu/text/NumberFormat.html) = NumberFormat.getInstance(Locale.getDefault())) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)<br>This class is responsible for the expense details view model. |
| [ListUiState](-list-ui-state/index.md) | [androidJvm]<br>interface [ListUiState](-list-ui-state/index.md)<br>This class is responsible for the list UI state. |
| [ListViewModel](-list-view-model/index.md) | [androidJvm]<br>class [ListViewModel](-list-view-model/index.md)(val expenseRepository: [ExpenseRepository](../com.example.expensesage.data.expenses/-expense-repository/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)<br>This class is responsible for the list view model. |
| [MainViewModel](-main-view-model/index.md) | [androidJvm]<br>class [MainViewModel](-main-view-model/index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)<br>Main ViewModel class that is used to store the state of the app |
| [MapUiState](-map-ui-state/index.md) | [androidJvm]<br>interface [MapUiState](-map-ui-state/index.md)<br>This class is responsible for the map UI state. |
| [SettingsViewModel](-settings-view-model/index.md) | [androidJvm]<br>class [SettingsViewModel](-settings-view-model/index.md)(changeInterval: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), userSettings: [UserSettingsService](../com.example.expensesage.data/-user-settings-service/index.md), expenseRepository: [ExpenseRepository](../com.example.expensesage.data.expenses/-expense-repository/index.md), currencyRepository: [CurrencyRepository](../com.example.expensesage.data.currencies/-currency-repository/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)<br>This class is responsible for the settings view model. |
| [SnackBarType](-snack-bar-type/index.md) | [androidJvm]<br>enum [SnackBarType](-snack-bar-type/index.md) : [Enum](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-enum/index.html)&lt;[SnackBarType](-snack-bar-type/index.md)&gt; |
| [StatisticUiState](-statistic-ui-state/index.md) | [androidJvm]<br>interface [StatisticUiState](-statistic-ui-state/index.md)<br>This interface is responsible for the statisticUiState. |
| [StatisticViewModel](-statistic-view-model/index.md) | [androidJvm]<br>class [StatisticViewModel](-statistic-view-model/index.md)(expenseRepository: [ExpenseRepository](../com.example.expensesage.data.expenses/-expense-repository/index.md), userPref: [UserSettingsService](../com.example.expensesage.data/-user-settings-service/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)<br>This class is responsible for the statistic view model. |

## Properties

| Name | Summary |
|---|---|
| [budgetFrequencyList](budget-frequency-list.md) | [androidJvm]<br>val [budgetFrequencyList](budget-frequency-list.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
