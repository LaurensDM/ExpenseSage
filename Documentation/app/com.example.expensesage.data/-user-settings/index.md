//[app](../../../index.md)/[com.example.expensesage.data](../index.md)/[UserSettings](index.md)

# UserSettings

[androidJvm]\
class [UserSettings](index.md)(dataStore: [DataStore](https://developer.android.com/reference/kotlin/androidx/datastore/core/DataStore.html)&lt;[Preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.html)&gt;) : [UserSettingsService](../-user-settings-service/index.md)

## Constructors

| | |
|---|---|
| [UserSettings](-user-settings.md) | [androidJvm]<br>constructor(dataStore: [DataStore](https://developer.android.com/reference/kotlin/androidx/datastore/core/DataStore.html)&lt;[Preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/Preferences.html)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [budget](budget.md) | [androidJvm]<br>open override val [budget](budget.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [budgetFrequency](budget-frequency.md) | [androidJvm]<br>open override val [budgetFrequency](budget-frequency.md): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [currency](currency.md) | [androidJvm]<br>open override val [currency](currency.md): Flow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; |
| [currencyModifier](currency-modifier.md) | [androidJvm]<br>open override val [currencyModifier](currency-modifier.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [firstBudgetChange](first-budget-change.md) | [androidJvm]<br>open override val [firstBudgetChange](first-budget-change.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [firstTime](first-time.md) | [androidJvm]<br>open override val [firstTime](first-time.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [moneyAvailable](money-available.md) | [androidJvm]<br>open override val [moneyAvailable](money-available.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [moneyOwed](money-owed.md) | [androidJvm]<br>open override val [moneyOwed](money-owed.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |
| [playSound](play-sound.md) | [androidJvm]<br>open override val [playSound](play-sound.md): Flow&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; |
| [soundVolume](sound-volume.md) | [androidJvm]<br>open override val [soundVolume](sound-volume.md): Flow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt; |

## Functions

| Name | Summary |
|---|---|
| [saveBudget](save-budget.md) | [androidJvm]<br>open suspend override fun [saveBudget](save-budget.md)(budget: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Save budget |
| [saveBudgetFrequency](save-budget-frequency.md) | [androidJvm]<br>open suspend override fun [saveBudgetFrequency](save-budget-frequency.md)(budgetFrequency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Save budget frequency |
| [saveCurrency](save-currency.md) | [androidJvm]<br>open suspend override fun [saveCurrency](save-currency.md)(currency: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Save currency |
| [saveCurrencyModifier](save-currency-modifier.md) | [androidJvm]<br>open suspend override fun [saveCurrencyModifier](save-currency-modifier.md)(currencyModifier: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Save currency modifier |
| [saveFirstBudgetChange](save-first-budget-change.md) | [androidJvm]<br>open suspend override fun [saveFirstBudgetChange](save-first-budget-change.md)(firstSettingChange: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Save first budget change |
| [saveFirstTime](save-first-time.md) | [androidJvm]<br>open suspend override fun [saveFirstTime](save-first-time.md)(firstTime: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Save first time |
| [saveMoneyAvailable](save-money-available.md) | [androidJvm]<br>open suspend override fun [saveMoneyAvailable](save-money-available.md)(moneyAvailable: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Save money available |
| [saveMoneyOwed](save-money-owed.md) | [androidJvm]<br>open suspend override fun [saveMoneyOwed](save-money-owed.md)(moneyOwed: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Save money owed |
| [saveSoundPreference](save-sound-preference.md) | [androidJvm]<br>open suspend override fun [saveSoundPreference](save-sound-preference.md)(playSound: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>Save sound preference |
| [saveSoundVolume](save-sound-volume.md) | [androidJvm]<br>open suspend override fun [saveSoundVolume](save-sound-volume.md)(soundVolume: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Save sound volume |
