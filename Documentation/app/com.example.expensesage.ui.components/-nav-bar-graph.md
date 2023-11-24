//[app](../../index.md)/[com.example.expensesage.ui.components](index.md)/[NavBarGraph](-nav-bar-graph.md)

# NavBarGraph

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [NavBarGraph](-nav-bar-graph.md)(navController: [NavHostController](https://developer.android.com/reference/kotlin/androidx/navigation/NavHostController.html), showModal: (expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md)?, isOwed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), modalType: [ModalType](../com.example.expensesage.ui.utils/-modal-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showAlert: (onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), title: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), showSnackbar: (message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), snackBarType: [SnackBarType](../com.example.expensesage.ui.viewModels/-snack-bar-type/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))

Navigation Bar Graph Composable (NavHost) for the app

#### Parameters

androidJvm

| | |
|---|---|
| navController | NavHostController |
| showModal | callback to show modal |
| showAlert | callback to show alert |
| showSnackbar | callback to show snackbar |
