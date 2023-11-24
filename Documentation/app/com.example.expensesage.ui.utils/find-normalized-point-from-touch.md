//[app](../../index.md)/[com.example.expensesage.ui.utils](index.md)/[findNormalizedPointFromTouch](find-normalized-point-from-touch.md)

# findNormalizedPointFromTouch

[androidJvm]\
fun [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html).[findNormalizedPointFromTouch](find-normalized-point-from-touch.md)(canvasCenter: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html)): [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html)

The touch point start from Canvas top left which ranges from (0,0) -> (canvas.width, canvas.height). We need to normalize this point so that it's based on the canvas center instead.
