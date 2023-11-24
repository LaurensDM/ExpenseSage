//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[CurrencyUIState](index.md)

# CurrencyUIState

interface [CurrencyUIState](index.md)

This interface is responsible for the currency UI state.

#### Inheritors

| |
|---|
| [Loading](-loading/index.md) |
| [Success](-success/index.md) |
| [Error](-error/index.md) |

## Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | [androidJvm]<br>data class [Error](-error/index.md)(val error: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) : [CurrencyUIState](index.md) |
| [Loading](-loading/index.md) | [androidJvm]<br>data object [Loading](-loading/index.md) : [CurrencyUIState](index.md) |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(val date: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [CurrencyUIState](index.md) |
