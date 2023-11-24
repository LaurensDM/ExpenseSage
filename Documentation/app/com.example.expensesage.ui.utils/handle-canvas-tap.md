//[app](../../index.md)/[com.example.expensesage.ui.utils](index.md)/[handleCanvasTap](handle-canvas-tap.md)

# handleCanvasTap

[androidJvm]\
fun [handleCanvasTap](handle-canvas-tap.md)(center: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html), tapOffset: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html), anglesList: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[DrawingAngles](-drawing-angles/index.md)&gt;, currentSelectedIndex: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), currentStrokeValues: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)&gt;, onItemSelected: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onItemDeselected: ([Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {}, onNoItemSelected: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {})

Calculate the drawing angles for each data point. The angles are calculated based on the total amount of the data points.

#### Parameters

androidJvm

| | |
|---|---|
| center | The center of the canvas |
| tapOffset | The offset of the tap |
| anglesList | The list of angles |
| currentSelectedIndex | The current selected index |
| currentStrokeValues | The current stroke values |
| onItemSelected | The on item selected callback |
| onItemDeselected | The on item deselected callback |
| onNoItemSelected | The on no item selected callback |
