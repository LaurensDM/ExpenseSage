//[app](../../index.md)/[com.example.expensesage.ui.components](index.md)/[CurrencyText](-currency-text.md)

# CurrencyText

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [CurrencyText](-currency-text.md)(currency: StateFlow&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, moneyAvailable: StateFlow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;, currencyModifier: StateFlow&lt;[Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)&gt;)

Currency Text

#### Parameters

androidJvm

| | |
|---|---|
| currency | StateFlow<String> Currency code (e.g. EUR) |
| moneyAvailable | StateFlow<Double> Amount of money available |
| currencyModifier | StateFlow<Double> Currency modifier (e.g. 1.0) |
