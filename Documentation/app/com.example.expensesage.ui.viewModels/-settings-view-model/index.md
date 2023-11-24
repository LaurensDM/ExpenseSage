//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[SettingsViewModel](index.md)

# SettingsViewModel

[androidJvm]\
class [SettingsViewModel](index.md)(changeInterval: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), userSettings: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), expenseRepository: [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md), currencyRepository: [CurrencyRepository](../../com.example.expensesage.data.currencies/-currency-repository/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

This class is responsible for the settings view model.

## Constructors

| | |
|---|---|
| [SettingsViewModel](-settings-view-model.md) | [androidJvm]<br>constructor(changeInterval: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), userSettings: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), expenseRepository: [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md), currencyRepository: [CurrencyRepository](../../com.example.expensesage.data.currencies/-currency-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [budget](budget.md) | [androidJvm]<br>var [budget](budget.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [budgetFrequencyState](budget-frequency-state.md) | [androidJvm]<br>var [budgetFrequencyState](budget-frequency-state.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [currency](currency.md) | [androidJvm]<br>var [currency](currency.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [changeBudget](change-budget.md) | [androidJvm]<br>fun [changeBudget](change-budget.md)()<br>This function changes the budget. |
| [changeCurrency](change-currency.md) | [androidJvm]<br>fun [changeCurrency](change-currency.md)(newCurrency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>This function changes the sound preference. |
| [changeSoundVolume](change-sound-volume.md) | [androidJvm]<br>fun [changeSoundVolume](change-sound-volume.md)(newVolume: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>This function changes the sound volume. |
| [getCurrency](get-currency.md) | [androidJvm]<br>fun [getCurrency](get-currency.md)(): StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>This function returns the currency. |
| [getCurrencyModifier](get-currency-modifier.md) | [androidJvm]<br>fun [getCurrencyModifier](get-currency-modifier.md)(): StateFlow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>This function returns the currency modifier. |
| [getMoneyAvailable](get-money-available.md) | [androidJvm]<br>fun [getMoneyAvailable](get-money-available.md)(): StateFlow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>This function gets the budget frequency. |
| [getSoundPreference](get-sound-preference.md) | [androidJvm]<br>fun [getSoundPreference](get-sound-preference.md)(): StateFlow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt;<br>This function returns the sound preference. |
| [getSoundVolume](get-sound-volume.md) | [androidJvm]<br>fun [getSoundVolume](get-sound-volume.md)(): StateFlow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;<br>This function returns the sound volume |
| [turnOffSound](turn-off-sound.md) | [androidJvm]<br>fun [turnOffSound](turn-off-sound.md)()<br>This function turns the sound off. |
| [updateBudget](update-budget.md) | [androidJvm]<br>fun [updateBudget](update-budget.md)(newBudget: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>This function updates the budget. |
| [updateBudgetFrequency](update-budget-frequency.md) | [androidJvm]<br>fun [updateBudgetFrequency](update-budget-frequency.md)(budgetFrequency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>This function updates the budget frequency. |
