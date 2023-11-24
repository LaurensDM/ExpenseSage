//[app](../../index.md)/[com.example.expensesage.ui.screens](index.md)/[ExpensesView](-expenses-view.md)

# ExpensesView

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ExpensesView](-expenses-view.md)(mapUiState: [MapUiState](../com.example.expensesage.ui.viewModels/-map-ui-state/index.md), retry: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, showModal: (expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md)?, isOwed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), modalType: [ModalType](../com.example.expensesage.ui.utils/-modal-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showAlert: (onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

This function is responsible for the expenses view.

#### Parameters

androidJvm

| | |
|---|---|
| mapUiState | The map ui state |
| retry | The retry function |
| showModal | The show modal function |
| showAlert | The show alert function |
