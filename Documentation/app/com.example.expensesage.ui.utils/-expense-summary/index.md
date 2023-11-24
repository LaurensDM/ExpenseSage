//[app](../../../index.md)/[com.example.expensesage.ui.utils](../index.md)/[ExpenseSummary](index.md)

# ExpenseSummary

[androidJvm]\
data class [ExpenseSummary](index.md)(val categoryData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt; = emptyList(), val monthlyData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt; = emptyList(), val totalSpent: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0, val owedTotal: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0, val moneySaved: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt; = emptyList())

This class is responsible for the expense summary.

## Constructors

| | |
|---|---|
| [ExpenseSummary](-expense-summary.md) | [androidJvm]<br>constructor(categoryData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt; = emptyList(), monthlyData: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt; = emptyList(), totalSpent: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0, owedTotal: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0, moneySaved: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt; = emptyList()) |

## Properties

| Name | Summary |
|---|---|
| [categoryData](category-data.md) | [androidJvm]<br>val [categoryData](category-data.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt;<br>The category data |
| [moneySaved](money-saved.md) | [androidJvm]<br>val [moneySaved](money-saved.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt;<br>The money saved |
| [monthlyData](monthly-data.md) | [androidJvm]<br>val [monthlyData](monthly-data.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ExpenseSummaryItem](../-expense-summary-item/index.md)&gt;<br>The monthly data |
| [owedTotal](owed-total.md) | [androidJvm]<br>val [owedTotal](owed-total.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0<br>The owed total |
| [totalSpent](total-spent.md) | [androidJvm]<br>val [totalSpent](total-spent.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) = 0.0<br>The total spent |
