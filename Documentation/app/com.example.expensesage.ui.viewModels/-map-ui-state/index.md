//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[MapUiState](index.md)

# MapUiState

interface [MapUiState](index.md)

This class is responsible for the map UI state.

#### Inheritors

| |
|---|
| [Error](-error/index.md) |
| [Success](-success/index.md) |
| [Loading](-loading/index.md) |

## Types

| Name | Summary |
|---|---|
| [Error](-error/index.md) | [androidJvm]<br>data class [Error](-error/index.md)(val message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [MapUiState](index.md) |
| [Loading](-loading/index.md) | [androidJvm]<br>data object [Loading](-loading/index.md) : [MapUiState](index.md) |
| [Success](-success/index.md) | [androidJvm]<br>data class [Success](-success/index.md)(val expenses: StateFlow&lt;[Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[Pair](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/index.html)&lt;[Month](https://developer.android.com/reference/kotlin/java/time/Month.html), [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)&gt;, [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../../com.example.expensesage.data.expenses/-expense/index.md)&gt;&gt;&gt;) : [MapUiState](index.md) |
