//[app](../../index.md)/[com.example.expensesage.ui.components](index.md)/[ExpenseItem](-expense-item.md)

# ExpenseItem

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ExpenseItem](-expense-item.md)(expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md), modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, showModal: (expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md)?, isOwed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), modalType: [ModalType](../com.example.expensesage.ui.utils/-modal-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showAlert: (onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), dataViewModel: [ExpenseDetailsViewModel](../com.example.expensesage.ui.viewModels/-expense-details-view-model/index.md) = viewModel(factory = AppViewModelProvider.Factory))

Composable that displays an expense item

#### Parameters

androidJvm

| | |
|---|---|
| expense | the expense to display |
| modifier | the modifier to apply to this layout node |
| showModal | show the modal |
| showAlert | show the alert |
| dataViewModel | ExpenseDetailsViewModel: View model for expense details |
