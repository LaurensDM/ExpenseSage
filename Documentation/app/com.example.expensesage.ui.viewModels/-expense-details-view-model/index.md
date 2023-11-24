//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[ExpenseDetailsViewModel](index.md)

# ExpenseDetailsViewModel

[androidJvm]\
class [ExpenseDetailsViewModel](index.md)(userPref: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), expenseRepository: [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md), format: [NumberFormat](https://developer.android.com/reference/kotlin/android/icu/text/NumberFormat.html) = NumberFormat.getInstance(Locale.getDefault())) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

This class is responsible for the expense details view model.

## Constructors

| | |
|---|---|
| [ExpenseDetailsViewModel](-expense-details-view-model.md) | [androidJvm]<br>constructor(userPref: [UserSettingsService](../../com.example.expensesage.data/-user-settings-service/index.md), expenseRepository: [ExpenseRepository](../../com.example.expensesage.data.expenses/-expense-repository/index.md), format: [NumberFormat](https://developer.android.com/reference/kotlin/android/icu/text/NumberFormat.html) = NumberFormat.getInstance(Locale.getDefault())) |

## Properties

| Name | Summary |
|---|---|
| [amountError](amount-error.md) | [androidJvm]<br>var [amountError](amount-error.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [expenseDetailState](expense-detail-state.md) | [androidJvm]<br>var [expenseDetailState](expense-detail-state.md): [ExpenseDetail](../../com.example.expensesage.ui.utils/-expense-detail/index.md) |
| [nameError](name-error.md) | [androidJvm]<br>var [nameError](name-error.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [deleteExpense](delete-expense.md) | [androidJvm]<br>fun [deleteExpense](delete-expense.md)(expense: [Expense](../../com.example.expensesage.data.expenses/-expense/index.md))<br>This function deletes an expense. |
| [initializeState](initialize-state.md) | [androidJvm]<br>fun [initializeState](initialize-state.md)(expense: [Expense](../../com.example.expensesage.data.expenses/-expense/index.md))<br>This function initializes the state. |
| [payOwed](pay-owed.md) | [androidJvm]<br>fun [payOwed](pay-owed.md)(expense: [Expense](../../com.example.expensesage.data.expenses/-expense/index.md))<br>This function pays an expense. |
| [resetState](reset-state.md) | [androidJvm]<br>fun [resetState](reset-state.md)()<br>This function resets the state. |
| [saveExpense](save-expense.md) | [androidJvm]<br>fun [saveExpense](save-expense.md)()<br>This function saves an expense. |
| [updateExpense](update-expense.md) | [androidJvm]<br>fun [updateExpense](update-expense.md)(originalExpense: [Expense](../../com.example.expensesage.data.expenses/-expense/index.md))<br>This function updates an expense. |
| [updateState](update-state.md) | [androidJvm]<br>fun [updateState](update-state.md)(expense: [ExpenseDetail](../../com.example.expensesage.ui.utils/-expense-detail/index.md))<br>This function updates the state. |
