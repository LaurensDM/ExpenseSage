package com.example.expensesage.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.animation.simpleChartAnimation
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer

//import com.himanshoe.charty.common.ChartDataCollection
//import com.himanshoe.charty.common.config.StartAngle
//import com.himanshoe.charty.pie.PieChart
//import com.himanshoe.charty.pie.config.PieChartConfig
//import com.himanshoe.charty.pie.model.PieData

@Composable
fun SummaryScreen() {
//    val chartData = listOf(
//        PieData(10f, "Jan", Color.Green),
//        PieData(20f, "Feb", Color.Blue),
//        PieData(30f, "Mar", Color.Red),
//        PieData(40f, "Apr", Color.Yellow),
//    )
//    PieChart(
//        dataCollection = ChartDataCollection(chartData),
//        modifier = Modifier.fillMaxSize(),
//        pieChartConfig = PieChartConfig(true, true, StartAngle.Zero)
//    )
    val chartData = listOf(
        PieChartData.Slice(10f, MaterialTheme.colorScheme.primary),
        PieChartData.Slice(20f, MaterialTheme.colorScheme.inversePrimary),
        PieChartData.Slice(30f, MaterialTheme.colorScheme.secondary),
        PieChartData.Slice(40f, MaterialTheme.colorScheme.tertiaryContainer),
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PieChart(
            pieChartData = PieChartData(chartData),
            modifier = Modifier.size(256.dp),
            animation = simpleChartAnimation(),
            sliceDrawer = SimpleSliceDrawer()
        )
    }


//    val chartData = listOf(
//        PieChartData.Slice("January", 10f, Color.Green),
//        PieChartData.Slice("February", 20f, Color.Blue),
//        PieChartData.Slice("March", 30f, Color.Red),
//        PieChartData.Slice("April", 40f, Color.Yellow),
//
//        )
//
//
//    DonutPieChart(
//        modifier = Modifier.size(256.dp),
//        pieChartData = PieChartData(chartData, PlotType.Donut),
//        pieChartConfig = PieChartConfig()
//    )
}

