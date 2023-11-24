//[app](../../index.md)/[com.example.expensesage.ui.components](index.md)/[EditForm](-edit-form.md)

# EditForm

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [EditForm](-edit-form.md)(expenseState: [ExpenseDetail](../com.example.expensesage.ui.utils/-expense-detail/index.md), onValueChange: ([ExpenseDetail](../com.example.expensesage.ui.utils/-expense-detail/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onDoneClicked: ([Expense](../com.example.expensesage.data.expenses/-expense/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), originalExpense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md), nameError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), amountError: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html), decimalFormatter: [DecimalFormatter](../com.example.expensesage.ui.utils/-decimal-formatter/index.md) = DecimalFormatter())

Edit Form

#### Parameters

androidJvm

| | |
|---|---|
| expenseState | ExpenseDetail State |
| onValueChange | (ExpenseDetail) -> Unit:  Callback to update state |
| onDoneClicked | (Expense) -> Unit: Callback to update expense |
| originalExpense | Expense: Original expense |
| nameError | Boolean: Name error |
| amountError | Boolean: Amount error |
| decimalFormatter | DecimalFormatter: Decimal formatter for currency |
