//[app](../../index.md)/[com.example.expensesage.ui.screens](index.md)/[CurrencySettings](-currency-settings.md)

# CurrencySettings

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [CurrencySettings](-currency-settings.md)(showSnackBar: (message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), snackBarType: [SnackBarType](../com.example.expensesage.ui.viewModels/-snack-bar-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showAlert: (onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), settingsViewModel: [SettingsViewModel](../com.example.expensesage.ui.viewModels/-settings-view-model/index.md) = viewModel(factory = AppViewModelProvider.Factory))

Composable that displays the currency settings

#### Parameters

androidJvm

| | |
|---|---|
| showSnackBar | The show snack bar function |
| showAlert | The show alert function |
| settingsViewModel | The settings view model |
