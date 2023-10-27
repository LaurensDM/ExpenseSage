package com.example.expensesage.ui.components

import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.expensesage.ui.utils.DonutChartData
import com.example.expensesage.ui.utils.DonutChartDataCollection
import com.example.expensesage.ui.utils.DonutChartState
import com.example.expensesage.ui.utils.DrawingAngles
import com.example.expensesage.ui.utils.STROKE_SIZE_UNSELECTED
import com.example.expensesage.ui.utils.calculateGapAngle
import com.example.expensesage.ui.utils.findSweepAngle
import com.example.expensesage.ui.utils.handleCanvasTap

@Composable
fun DonutChart(
    modifier: Modifier = Modifier,
    chartSize: Dp = 350.dp,
    data: DonutChartDataCollection,
    gapPercentage: Float = 0.04f,
    selectionView: @Composable (selectedItem: DonutChartData?) -> Unit = {},
) {
    var selectedIndex by remember { mutableIntStateOf(-1) }
    val animationTargetState = (0..data.items.size).map {
        remember { mutableStateOf(DonutChartState()) }
    }
    val animValues = (0..data.items.size).map {
        animateDpAsState(
            targetValue = animationTargetState[it].value.stroke,
            animationSpec = TweenSpec(700),
            label = "",
        )
    }
    val anglesList: MutableList<DrawingAngles> = remember { mutableListOf() }
    val gapAngle = data.calculateGapAngle(gapPercentage)
    var center = Offset(0f, 0f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Canvas(
            modifier = Modifier
                .size(chartSize)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { tapOffset ->
                            handleCanvasTap(
                                center = center,
                                tapOffset = tapOffset,
                                anglesList = anglesList,
                                currentSelectedIndex = selectedIndex,
                                currentStrokeValues = animationTargetState.map { it.value.stroke.toPx() },
                                onItemSelected = { index ->
                                    selectedIndex = index
                                    animationTargetState[index].value = DonutChartState(
                                        DonutChartState.State.Selected,
                                    )
                                },
                                onItemDeselected = { index ->
                                    animationTargetState[index].value = DonutChartState(
                                        DonutChartState.State.Unselected,
                                    )
                                },
                                onNoItemSelected = {
                                    selectedIndex = -1
                                },
                            )
                        },
                    )
                },
            onDraw = {
                val defaultStrokeWidth = STROKE_SIZE_UNSELECTED.toPx()
                center = this.center
                anglesList.clear()
                var lastAngle = 0f
                data.items.forEachIndexed { ind, item ->
                    val sweepAngle = data.findSweepAngle(ind, gapPercentage)
                    anglesList.add(DrawingAngles(lastAngle, sweepAngle))
                    val strokeWidth = animValues[ind].value.toPx()
                    drawArc(
                        color = item.color,
                        startAngle = lastAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = Offset(defaultStrokeWidth / 2, defaultStrokeWidth / 2),
                        style = Stroke(strokeWidth, cap = StrokeCap.Butt),
                        size = Size(
                            size.width - defaultStrokeWidth,
                            size.height - defaultStrokeWidth,
                        ),
                    )
                    lastAngle += sweepAngle + gapAngle
                }
            },
        )
        selectionView(if (selectedIndex >= 0) data.items[selectedIndex] else null)
    }
}
