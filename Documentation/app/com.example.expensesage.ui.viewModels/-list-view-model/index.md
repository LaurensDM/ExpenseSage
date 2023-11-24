//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[ListViewModel](index.md)

# ListViewModel

[androidJvm]\
class [ListViewModel](index.md)(val expenseRepository: [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md)) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

This class is responsible for the list view model.

## Constructors

| | |
|---|---|
| [ListViewModel](-list-view-model.md) | [androidJvm]<br>constructor(expenseRepository: [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md)) |

## Properties

| Name | Summary |
|---|---|
| [expenseRepository](expense-repository.md) | [androidJvm]<br>val [expenseRepository](expense-repository.md): [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md)<br>The expense repository |
| [listUiState](list-ui-state.md) | [androidJvm]<br>var [listUiState](list-ui-state.md): [ListUiState](../-list-ui-state/index.md) |
| [mapUiState](map-ui-state.md) | [androidJvm]<br>var [mapUiState](map-ui-state.md): [MapUiState](../-map-ui-state/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [get5Expenses](get5-expenses.md) | [androidJvm]<br>fun [get5Expenses](get5-expenses.md)()<br>This function gets the top 5 expenses. |
| [getExpenses](get-expenses.md) | [androidJvm]<br>fun [getExpenses](get-expenses.md)(owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html))<br>This function gets the expenses. |
