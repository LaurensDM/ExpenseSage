//[app](../../index.md)/[com.example.expensesage.ui.components](index.md)/[Edit](-edit.md)

# Edit

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [Edit](-edit.md)(selectedExpense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md), onDialogDismiss: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), dataViewModel: [ExpenseDetailsViewModel](../com.example.expensesage.ui.viewModels/-expense-details-view-model/index.md) = viewModel(factory = AppViewModelProvider.Factory))

Edit Dialog

#### Parameters

androidJvm

| | |
|---|---|
| selectedExpense | Expense: Selected expense |
| onDialogDismiss | () -> Unit: Callback to dismiss dialog |
| dataViewModel | ExpenseDetailsViewModel: View model for expense details |
