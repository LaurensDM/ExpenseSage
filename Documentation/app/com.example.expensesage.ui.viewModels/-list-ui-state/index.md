//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[ListUiState](index.md)

# ListUiState

interface [ListUiState](index.md)

This class is responsible for the list UI state.

#### Inheritors

| |
|---|
| [Error](-error/index.md) |
| [Success](-success/index.md) |
| [Loading](-loading/index.md) |

## Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | [androidJvm]<br>data class [Error](-error/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [ListUiState](index.md) |
| [Loading](-loading/index.md) | [androidJvm]<br>data object [Loading](-loading/index.md) : [ListUiState](index.md) |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(val expenses: StateFlow&lt;[List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../../com.example.expensesage.data.expenses/-expense/index.md)&gt;&gt;) : [ListUiState](index.md) |
