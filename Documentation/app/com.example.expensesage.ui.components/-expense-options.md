//[app](../../index.md)/[com.example.expensesage.ui.components](index.md)/[ExpenseOptions](-expense-options.md)

# ExpenseOptions

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ExpenseOptions](-expense-options.md)(onEditClicked: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onDetailClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), onPayedClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onDeleteClick: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html), expense: [Expense](../com.example.expensesage.data.expenses/-expense/index.md))

Composable that displays the expense options on the expense item.

#### Parameters

androidJvm

| | |
|---|---|
| onEditClicked | callback for edit button |
| onDetailClick | callback for detail button |
| onPayedClick | callback for payed button |
| onDeleteClick | callback for delete button |
| expense | the expense to display |
