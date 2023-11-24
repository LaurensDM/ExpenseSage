//[app](../../index.md)/[com.example.expensesage.ui.screens](index.md)/[TopExpensesList](-top-expenses-list.md)

# TopExpensesList

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [TopExpensesList](-top-expenses-list.md)(expenses: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Expense](../com.example.expensesage.data.expenses/-expense/index.md)&gt;, showModal: (expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md)?, isOwed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), modalType: [ModalType](../com.example.expensesage.ui.utils/-modal-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showAlert: (onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Composable that displays the 5 latest expenses.

#### Parameters

androidJvm

| | |
|---|---|
| expenses | The expenses |
| showModal | The show modal function |
| showAlert | The show alert function |
