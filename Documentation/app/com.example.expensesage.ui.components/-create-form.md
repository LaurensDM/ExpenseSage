//[app](../../index.md)/[com.example.expensesage.ui.components](index.md)/[CreateForm](-create-form.md)

# CreateForm

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [CreateForm](-create-form.md)(expenseState: [ExpenseDetail](../com.example.expensesage.ui.utils/-expense-detail/index.md), updateState: ([ExpenseDetail](../com.example.expensesage.ui.utils/-expense-detail/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onDoneClicked: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, nameError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), amountError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), decimalFormatter: [DecimalFormatter](../com.example.expensesage.ui.utils/-decimal-formatter/index.md) = DecimalFormatter())

Create Expense Form

#### Parameters

androidJvm

| | |
|---|---|
| expenseState | ExpenseDetail state |
| updateState | update ExpenseDetail state |
| onDoneClicked | onDoneClicked function |
| nameError | nameError state boolean |
| amountError | amountError state boolean |
| decimalFormatter | DecimalFormatter object to format amount input |
