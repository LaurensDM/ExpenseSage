package com.example.expensesage.ui.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.pow
import kotlin.math.sqrt

private const val TOTAL_ANGLE = 360.0f
val STROKE_SIZE_UNSELECTED = 40.dp
val STROKE_SIZE_SELECTED = 60.dp

data class DonutChartData(
    val amount: Float,
    val color: Color,
    val title: String,
)

data class DonutChartDataCollection(
    var items: List<DonutChartData>,
) {
    internal var totalAmount: Float = items.sumOf { it.amount.toDouble() }.toFloat()
        private set
}

data class DrawingAngles(val start: Float, val end: Float)

fun DrawingAngles.isInsideAngle(angle: Float) = angle > this.start && angle < this.start + this.end

class DonutChartState(
    private val state: State = State.Unselected,
) {
    val stroke: Dp
        get() = when (state) {
            State.Selected -> STROKE_SIZE_SELECTED
            State.Unselected -> STROKE_SIZE_UNSELECTED
        }

    enum class State {
        Selected, Unselected
    }
}

fun handleCanvasTap(
    center: Offset,
    tapOffset: Offset,
    anglesList: List<DrawingAngles>,
    currentSelectedIndex: Int,
    currentStrokeValues: List<Float>,
    onItemSelected: (Int) -> Unit = {},
    onItemDeselected: (Int) -> Unit = {},
    onNoItemSelected: () -> Unit = {},
) {
    val normalized = tapOffset.findNormalizedPointFromTouch(center)
    val touchAngle =
        calculateTouchAngleAccordingToCanvas(center, normalized)
    val distance = findTouchDistanceFromCenter(center, normalized)

    var selectedIndex = -1
    var newDataTapped = false

    anglesList.forEachIndexed { ind, angle ->
        val stroke = currentStrokeValues[ind]
        if (angle.isInsideAngle(touchAngle)) {
            if (distance > (center.x - stroke) &&
                distance < (center.x)
            ) { // since it's a square center.x or center.y will be the same
                selectedIndex = ind
                newDataTapped = true
            }
        }
    }

    if (selectedIndex >= 0 && newDataTapped) {
        onItemSelected(selectedIndex)
    }
    if (currentSelectedIndex >= 0) {
        onItemDeselected(currentSelectedIndex)
        if (currentSelectedIndex == selectedIndex || !newDataTapped) {
            onNoItemSelected()
        }
    }
}

/**
 * Find the distance based on two points in a graph. Calculated using the pythagorean theorem.
 */
fun findTouchDistanceFromCenter(center: Offset, touch: Offset) =
    sqrt((touch.x - center.x).pow(2) + (touch.y - center.y).pow(2))

/**
 * The touch point start from Canvas top left which ranges from (0,0) -> (canvas.width, canvas.height).
 * We need to normalize this point so that it's based on the canvas center instead.
 */
fun Offset.findNormalizedPointFromTouch(canvasCenter: Offset) =
    Offset(this.x, canvasCenter.y + (canvasCenter.y - this.y))

/**
 * Calculate the touch angle based on the canvas center. Then adjust the angle so that
 * drawing starts from the 4th quadrant instead of the first.
 */
fun calculateTouchAngleAccordingToCanvas(canvasCenter: Offset, normalizedPoint: Offset): Float {
    val angle = calculateTouchAngleInDegrees(canvasCenter, normalizedPoint)
    return adjustAngleToCanvas(angle).toFloat()
}

/**
 * Calculate touch angle in radian using atan2(). Afterwards, convert the radian to degrees to be
 * compared to other data points.
 */
fun calculateTouchAngleInDegrees(canvasCenter: Offset, normalizedPoint: Offset): Double {
    val touchInRadian = kotlin.math.atan2(
        normalizedPoint.y - canvasCenter.y,
        normalizedPoint.x - canvasCenter.x,
    )
    return touchInRadian * -180 / Math.PI // Convert radians to angle in degrees
}

/**
 * Start from 4th quadrant going to 1st quadrant, degrees ranging from 0 to 360
 */
fun adjustAngleToCanvas(angle: Double) = (angle + TOTAL_ANGLE) % TOTAL_ANGLE

/**
 * Calculate the gap width between the arcs based on [gapPercentage]. The percentage is applied
 * to the average count to determine the width in pixels.
 */
fun DonutChartDataCollection.calculateGap(gapPercentage: Float): Float {
    if (this.items.isEmpty()) return 0f

    return (this.totalAmount / this.items.size) * gapPercentage
}

/**
 * Returns the total data points including the individual gap widths indicated by the
 * [gapPercentage].
 */
fun DonutChartDataCollection.getTotalAmountWithGapIncluded(gapPercentage: Float): Float {
    val gap = this.calculateGap(gapPercentage)
    return this.totalAmount + (this.items.size * gap)
}

/**
 * Calculate the sweep angle of an arc including the gap as well. The gap is derived based
 * on [gapPercentage].
 */
fun DonutChartDataCollection.calculateGapAngle(gapPercentage: Float): Float {
    val gap = this.calculateGap(gapPercentage)
    val totalAmountWithGap = this.getTotalAmountWithGapIncluded(gapPercentage)

    return (gap / totalAmountWithGap) * TOTAL_ANGLE
}

/**
 * Returns the sweep angle of a given point in the [DonutChartDataCollection]. This calculations
 * takes the gap between arcs into the account.
 */
fun DonutChartDataCollection.findSweepAngle(
    index: Int,
    gapPercentage: Float,
): Float {
    val amount = items[index].amount
    val gap = this.calculateGap(gapPercentage)
    val totalWithGap = getTotalAmountWithGapIncluded(gapPercentage)
    val gapAngle = this.calculateGapAngle(gapPercentage)
    return ((((amount + gap) / totalWithGap) * TOTAL_ANGLE)) - gapAngle
}
