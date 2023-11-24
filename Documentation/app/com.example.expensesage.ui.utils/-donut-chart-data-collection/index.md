//[app](../../../index.md)/[com.example.expensesage.ui.utils](../index.md)/[DonutChartDataCollection](index.md)

# DonutChartDataCollection

[androidJvm]\
data class [DonutChartDataCollection](index.md)(var items: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DonutChartData](../-donut-chart-data/index.md)&gt;)

## Constructors

| | |
|---|---|
| [DonutChartDataCollection](-donut-chart-data-collection.md) | [androidJvm]<br>constructor(items: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DonutChartData](../-donut-chart-data/index.md)&gt;) |

## Properties

| Name | Summary |
|---|---|
| [items](items.md) | [androidJvm]<br>var [items](items.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DonutChartData](../-donut-chart-data/index.md)&gt; |

## Functions

| Name | Summary |
|---|---|
| [calculateGap](../calculate-gap.md) | [androidJvm]<br>fun [DonutChartDataCollection](index.md).[calculateGap](../calculate-gap.md)(gapPercentage: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>Calculate the gap width between the arcs based on [gapPercentage](../calculate-gap.md). The percentage is applied to the average count to determine the width in pixels. |
| [calculateGapAngle](../calculate-gap-angle.md) | [androidJvm]<br>fun [DonutChartDataCollection](index.md).[calculateGapAngle](../calculate-gap-angle.md)(gapPercentage: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>Calculate the sweep angle of an arc including the gap as well. The gap is derived based on [gapPercentage](../calculate-gap-angle.md). |
| [findSweepAngle](../find-sweep-angle.md) | [androidJvm]<br>fun [DonutChartDataCollection](index.md).[findSweepAngle](../find-sweep-angle.md)(index: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), gapPercentage: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>Returns the sweep angle of a given point in the [DonutChartDataCollection](index.md). This calculations takes the gap between arcs into the account. |
| [getTotalAmountWithGapIncluded](../get-total-amount-with-gap-included.md) | [androidJvm]<br>fun [DonutChartDataCollection](index.md).[getTotalAmountWithGapIncluded](../get-total-amount-with-gap-included.md)(gapPercentage: [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)<br>Returns the total data points including the individual gap widths indicated by the [gapPercentage](../get-total-amount-with-gap-included.md). |
