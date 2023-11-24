//[app](../../../index.md)/[com.example.expensesage.data](../index.md)/[UserSettingsService](index.md)

# UserSettingsService

interface [UserSettingsService](index.md)

#### Inheritors

| |
|---|
| [UserSettings](../-user-settings/index.md) |

## Properties

| Name | Summary |
|---|---|
| [budget](budget.md) | [androidJvm]<br>abstract val [budget](budget.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [budgetFrequency](budget-frequency.md) | [androidJvm]<br>abstract val [budgetFrequency](budget-frequency.md): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [currency](currency.md) | [androidJvm]<br>abstract val [currency](currency.md): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [currencyModifier](currency-modifier.md) | [androidJvm]<br>abstract val [currencyModifier](currency-modifier.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [firstBudgetChange](first-budget-change.md) | [androidJvm]<br>abstract val [firstBudgetChange](first-budget-change.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [firstTime](first-time.md) | [androidJvm]<br>abstract val [firstTime](first-time.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [moneyAvailable](money-available.md) | [androidJvm]<br>abstract val [moneyAvailable](money-available.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [moneyOwed](money-owed.md) | [androidJvm]<br>abstract val [moneyOwed](money-owed.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [playSound](play-sound.md) | [androidJvm]<br>abstract val [playSound](play-sound.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [soundVolume](sound-volume.md) | [androidJvm]<br>abstract val [soundVolume](sound-volume.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [saveBudget](save-budget.md) | [androidJvm]<br>abstract suspend fun [saveBudget](save-budget.md)(budget: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) |
| [saveBudgetFrequency](save-budget-frequency.md) | [androidJvm]<br>abstract suspend fun [saveBudgetFrequency](save-budget-frequency.md)(budgetFrequency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [saveCurrency](save-currency.md) | [androidJvm]<br>abstract suspend fun [saveCurrency](save-currency.md)(currency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |
| [saveCurrencyModifier](save-currency-modifier.md) | [androidJvm]<br>abstract suspend fun [saveCurrencyModifier](save-currency-modifier.md)(currencyModifier: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) |
| [saveFirstBudgetChange](save-first-budget-change.md) | [androidJvm]<br>abstract suspend fun [saveFirstBudgetChange](save-first-budget-change.md)(firstBudgetChange: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [saveFirstTime](save-first-time.md) | [androidJvm]<br>abstract suspend fun [saveFirstTime](save-first-time.md)(firstTime: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [saveMoneyAvailable](save-money-available.md) | [androidJvm]<br>abstract suspend fun [saveMoneyAvailable](save-money-available.md)(moneyAvailable: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) |
| [saveMoneyOwed](save-money-owed.md) | [androidJvm]<br>abstract suspend fun [saveMoneyOwed](save-money-owed.md)(moneyOwed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) |
| [saveSoundPreference](save-sound-preference.md) | [androidJvm]<br>abstract suspend fun [saveSoundPreference](save-sound-preference.md)(playSound: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)) |
| [saveSoundVolume](save-sound-volume.md) | [androidJvm]<br>abstract suspend fun [saveSoundVolume](save-sound-volume.md)(soundVolume: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)) |
