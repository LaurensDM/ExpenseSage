//[app](../../index.md)/[com.example.expensesage.ui.utils](index.md)/[calculateTouchAngleAccordingToCanvas](calculate-touch-angle-according-to-canvas.md)

# calculateTouchAngleAccordingToCanvas

[androidJvm]\
fun [calculateTouchAngleAccordingToCanvas](calculate-touch-angle-according-to-canvas.md)(canvasCenter: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html), normalizedPoint: [Offset](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/Offset.html)): [Float](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)

Calculate the touch angle based on the canvas center. Then adjust the angle so that drawing starts from the 4th quadrant instead of the first.
