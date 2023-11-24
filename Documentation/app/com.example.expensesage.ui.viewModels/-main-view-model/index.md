//[app](../../../index.md)/[com.example.expensesage.ui.viewModels](../index.md)/[MainViewModel](index.md)

# MainViewModel

[androidJvm]\
class [MainViewModel](index.md) : [ViewModel](https://developer.android.com/reference/kotlin/androidx/lifecycle/ViewModel.html)

Main ViewModel class that is used to store the state of the app

## Constructors

| | |
|---|---|
| [MainViewModel](-main-view-model.md) | [androidJvm]<br>constructor() |

## Properties

| Name | Summary |
|---|---|
| [alertOnCancel](alert-on-cancel.md) | [androidJvm]<br>var [alertOnCancel](alert-on-cancel.md): () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [alertOnConfirm](alert-on-confirm.md) | [androidJvm]<br>var [alertOnConfirm](alert-on-confirm.md): () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [alertText](alert-text.md) | [androidJvm]<br>var [alertText](alert-text.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [alertTextSubject](alert-text-subject.md) | [androidJvm]<br>var [alertTextSubject](alert-text-subject.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [currentModalType](current-modal-type.md) | [androidJvm]<br>var [currentModalType](current-modal-type.md): [ModalType](../../com.example.expensesage.ui.utils/-modal-type/index.md) |
| [isAlertShown](is-alert-shown.md) | [androidJvm]<br>var [isAlertShown](is-alert-shown.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isDialogShown](is-dialog-shown.md) | [androidJvm]<br>var [isDialogShown](is-dialog-shown.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isOwed](is-owed.md) | [androidJvm]<br>var [isOwed](is-owed.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [selectedExpense](selected-expense.md) | [androidJvm]<br>var [selectedExpense](selected-expense.md): [Expense](../../com.example.expensesage.data.expenses/-expense/index.md) |
| [snackbarHostState](snackbar-host-state.md) | [androidJvm]<br>val [snackbarHostState](snackbar-host-state.md): [SnackbarHostState](https://developer.android.com/reference/kotlin/androidx/compose/material3/SnackbarHostState.html) |
| [snackbarType](snackbar-type.md) | [androidJvm]<br>var [snackbarType](snackbar-type.md): [SnackBarType](../-snack-bar-type/index.md) |

## Functions

| Name | Summary |
|---|---|
| [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524) | [androidJvm]<br>open fun [addCloseable](../-statistic-view-model/index.md#264516373%2FFunctions%2F-912451524)(@[NonNull](https://developer.android.com/reference/kotlin/androidx/annotation/NonNull.html)p0: [Closeable](https://developer.android.com/reference/kotlin/java/io/Closeable.html)) |
| [onDialogDismiss](on-dialog-dismiss.md) | [androidJvm]<br>fun [onDialogDismiss](on-dialog-dismiss.md)()<br>Function that is called when the user dismisses dialog. Hides dialog |
| [showAlert](show-alert.md) | [androidJvm]<br>fun [showAlert](show-alert.md)(onConfirm: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), text: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), subject: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), onCancel: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html))<br>Shows alert |
| [showModal](show-modal.md) | [androidJvm]<br>fun [showModal](show-modal.md)(expense: [Expense](../../com.example.expensesage.data.expenses/-expense/index.md)? = Expense(imageResourceId = R.drawable.cost, owed = false), owed: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, modalType: [ModalType](../../com.example.expensesage.ui.utils/-modal-type/index.md))<br>Shows modal |
| [showSnackBar](show-snack-bar.md) | [androidJvm]<br>fun [showSnackBar](show-snack-bar.md)(message: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), type: [SnackBarType](../-snack-bar-type/index.md))<br>Shows snackbar |
