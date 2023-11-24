//[app](../../index.md)/[com.example.expensesage.ui.screens](index.md)/[BottomTile](-bottom-tile.md)

# BottomTile

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [BottomTile](-bottom-tile.md)(listUiState: [ListUiState](../com.example.expensesage.ui.viewModels/-list-ui-state/index.md), retry: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, showModal: (expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md)?, isOwed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), modalType: [ModalType](../com.example.expensesage.ui.utils/-modal-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showAlert: (onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Composable that displays the bottom tile of the start screen. Has information about the latest expenses.

#### Parameters

androidJvm

| | |
|---|---|
| listUiState | The list ui state |
| retry | The retry function |
| showModal | The show modal function |
| showAlert | The show alert function |
