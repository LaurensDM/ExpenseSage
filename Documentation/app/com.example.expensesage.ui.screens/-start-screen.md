//[app](../../index.md)/[com.example.expensesage.ui.screens](index.md)/[StartScreen](-start-screen.md)

# StartScreen

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [StartScreen](-start-screen.md)(listViewModel: [ListViewModel](../com.example.expensesage.ui.viewModels/-list-view-model/index.md) = viewModel(factory = AppViewModelProvider.Factory), showModal: (expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md)?, isOwed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), modalType: [ModalType](../com.example.expensesage.ui.utils/-modal-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showAlert: (onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

This function is responsible for the start screen.

#### Parameters

androidJvm

| | |
|---|---|
| listViewModel | The list view model |
| showModal | The show modal function |
| showAlert | The show alert function |
