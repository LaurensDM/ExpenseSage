//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[StatisticUiState](index.md)

# StatisticUiState

interface [StatisticUiState](index.md)

This interface is responsible for the statisticUiState.

#### Inheritors

| |
|---|
| [Success](-success/index.md) |
| [Error](-error/index.md) |
| [Loading](-loading/index.md) |

## Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | [androidJvm]<br>data class [Error](-error/index.md)(val error: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [StatisticUiState](index.md) |
| [Loading](-loading/index.md) | [androidJvm]<br>data object [Loading](-loading/index.md) : [StatisticUiState](index.md) |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(val summary: [ExpenseSummary](../../com.example.expensesage.ui.utils/-expense-summary/index.md)) : [StatisticUiState](index.md) |
