package com.example.expensesage.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.CurrencyString
import com.example.expensesage.ui.components.DonutChart
import com.example.expensesage.ui.theme.* // ktlint-disable no-wildcard-imports
import com.example.expensesage.ui.utils.DonutChartData
import com.example.expensesage.ui.utils.DonutChartDataCollection
import com.example.expensesage.ui.utils.ExpenseSummary
import com.example.expensesage.ui.utils.ExpenseSummaryItem
import com.example.expensesage.ui.viewModels.SettingsViewModel
import com.example.expensesage.ui.viewModels.StatisticUiState
import com.example.expensesage.ui.viewModels.StatisticViewModel
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt

@Composable
fun SummaryScreen(
    viewModel: StatisticViewModel = viewModel(factory = AppViewModelProvider.Factory),
    settings: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    when (viewModel.statisticUiState) {
        is StatisticUiState.Loading -> SummaryLoading()
        is StatisticUiState.Success -> Summary((viewModel.statisticUiState as StatisticUiState.Success).summary, settings.getCurrencyModifier())
        else -> Error(
            retryAction = { /*TODO*/ },
            error = (viewModel.statisticUiState as StatisticUiState.Error).error,
        )
    }
}

@Composable
fun Summary(summaryData: ExpenseSummary = ExpenseSummary(), currenncyModifier: StateFlow<Double>) {
    val currencyRate by currenncyModifier.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            Text(
                text = "Money spent by category",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
            )
        }
        item {
            CategoryChart(summaryData.donutChartData)
        }
        item {
            MonthChart(summaryData.monthlyData, currencyRate)
        }

//        Text(
//            text = "Total money spent since using this app: ${
//                CurrencyString(
//                    money = 4000.02,
//                    fractionDigits = 2,
//                )
//            }",
//            style = MaterialTheme.typography.displayMedium,
//            textAlign = TextAlign.Center,
//        )
    }
}

@Composable
fun CategoryChart(categoryData: List<ExpenseSummaryItem>) {
    val sliceColors = listOf(Slice1, Slice2, Slice3, Slice4, Slice5, Slice6)
    val chartData = DonutChartDataCollection(
        categoryData.mapIndexed { index, it ->
            DonutChartData(
                (it.totalExpense).toFloat(),
                sliceColors[index],
                it.subject,
            )
        },
//            DonutChartData(1200.0f, Slice1, title = "Food & Groceries"),
//            DonutChartData(1500.0f, Slice2, title = "Rent"),
//            DonutChartData(300.0f, Slice3, title = "Gas"),
//            DonutChartData(700.0f, Slice4, title = "Online Purchases"),
//            DonutChartData(300.0f, Slice5, title = "Clothing"),
//            DonutChartData(600.0f, Slice6, title = "Other"),
    )
    DonutChart(
        modifier = Modifier.padding(8.dp),
        data = chartData,
        gapPercentage = if (chartData.items.size <= 1) 0f else 0.01f,
    ) { selected ->
        AnimatedContent(targetState = selected, label = "") {
            val amount = it?.amount ?: chartData.totalAmount
            val text = it?.title ?: "Total"

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    CurrencyString(money = amount.toDouble(), 0),
                    style = moneyAmountStyle,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(text, style = itemTextStyle, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@Composable
fun MonthChart(monthData: List<ExpenseSummaryItem>, currencyRate: Double = 1.0) {
    val barColors = listOf(Bar1, Bar2, Bar3, Bar4, Bar5, Bar6, Bar7, Bar8, Bar9, Bar10, Bar11, Bar12)
    val chartData = BarChartData(
        bars = monthData.mapIndexed { index, it ->
            Bar(
                value = (it.totalExpense * currencyRate).toFloat(),
                label = it.subject.substring(5, 7),
                color = barColors[index],
            )
        },
    )
    BarChart(
        barChartData = chartData,
        modifier = Modifier.fillMaxWidth().height(256.dp).padding(bottom = 16.dp, start = 16.dp),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = MaterialTheme.colorScheme.onSurface,
            axisLineColor = MaterialTheme.colorScheme.secondary,
            axisLineThickness = 4.dp,
            labelValueFormatter = { value ->
                value.roundToInt().toString()
            },
        ),
        xAxisDrawer = SimpleXAxisDrawer(
            axisLineThickness = 4.dp,
            axisLineColor = MaterialTheme.colorScheme.secondary,
        ),
        labelDrawer = SimpleValueDrawer(
            drawLocation = SimpleValueDrawer.DrawLocation.XAxis,
            labelTextColor = MaterialTheme.colorScheme.onSurface,
        ),
    )
}

@Composable
fun SummaryLoading() {
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        CircularProgressIndicator()
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Please wait while we prepare your summary...",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
        )
    }
}
