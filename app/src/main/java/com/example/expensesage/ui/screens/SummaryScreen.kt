package com.example.expensesage.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.components.CurrencyString
import com.example.expensesage.ui.components.DonutChart
import com.example.expensesage.ui.theme.Slice1
import com.example.expensesage.ui.theme.Slice2
import com.example.expensesage.ui.theme.Slice3
import com.example.expensesage.ui.theme.Slice4
import com.example.expensesage.ui.theme.Slice5
import com.example.expensesage.ui.theme.Slice6
import com.example.expensesage.ui.theme.itemTextStyle
import com.example.expensesage.ui.theme.moneyAmountStyle
import com.example.expensesage.ui.utils.DonutChartData
import com.example.expensesage.ui.utils.DonutChartDataCollection
import com.example.expensesage.ui.utils.ExpenseSummary
import com.example.expensesage.ui.utils.ExpenseSummaryItem
import com.example.expensesage.ui.viewModels.StatisticUiState
import com.example.expensesage.ui.viewModels.StatisticViewModel
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.BarChartData.Bar
import com.github.tehras.charts.bar.renderer.label.SimpleValueDrawer
import com.github.tehras.charts.bar.renderer.xaxis.SimpleXAxisDrawer
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.github.tehras.charts.piechart.PieChart
import com.github.tehras.charts.piechart.PieChartData
import com.github.tehras.charts.piechart.renderer.SimpleSliceDrawer
import java.time.LocalDate
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlin.random.Random

val graphColors = listOf(Slice1, Slice2, Slice3, Slice4, Slice5, Slice6)

/**
 * This function is responsible for the summary screen.
 *
 * @param viewModel The statistic view model
 */
@Composable
fun SummaryScreen(
    viewModel: StatisticViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    when (viewModel.statisticUiState) {
        is StatisticUiState.Loading -> SummaryLoading()
        is StatisticUiState.Success -> Summary(
            (viewModel.statisticUiState as StatisticUiState.Success).summary,
        )

        else -> Error(
            retryAction = { viewModel.getData() },
            error = (viewModel.statisticUiState as StatisticUiState.Error).error,
        )
    }
}

/**
 * This function is responsible for the summary.
 *
 * @param summaryData The summary data
 */
@Composable
fun Summary(
    summaryData: ExpenseSummary = ExpenseSummary(),
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(64.dp),
    ) {
        item {
            PrimaryChart(totalSpent = summaryData.totalSpent, owedSpent = summaryData.owedTotal)
        }
        item {
            WeeklyComparison(summaryData.moneySaved)
        }
        item {
            CategoryChart(summaryData.categoryData)
        }
        item {
            MonthChart(summaryData.monthlyData)
        }
    }
}

/**
 * This function is responsible for the weekly comparison.
 *
 * @param moneySaved The money saved
 */
@Composable
fun WeeklyComparison(moneySaved: List<ExpenseSummaryItem>) {
    val chartData = BarChartData(
        bars =
        if (moneySaved.isEmpty()) emptyList()
        else if (moneySaved.size == 1) listOf(
            Bar(
                value = moneySaved[0].totalExpense.toFloat(),
                label = "This week \n ${CurrencyString(money = moneySaved[0].totalExpense, fractionDigits = 2)}",
                color = graphColors[4],
            ),
        ) else listOf(
            Bar(
                value = moneySaved[0].totalExpense.toFloat(),
                label = "Last week: ${CurrencyString(money = moneySaved[0].totalExpense, fractionDigits = 2)}",
                color = graphColors[0],
            ),
            Bar(
                value = moneySaved[1].totalExpense.toFloat(),
                label = "This week: ${CurrencyString(money = moneySaved[1].totalExpense, fractionDigits = 2)}",
                color = graphColors[1],
            ),
        ),
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
//            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        if (moneySaved.isEmpty()) {
            NoData()
        } else {
            Text(
                text = if (moneySaved.size == 1) "Money spent this week" else "Money spent last week vs this week",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(8.dp))
            BarChart(
                barChartData = chartData,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .padding(start = 32.dp),
                labelDrawer = SimpleValueDrawer(
                    labelTextColor = MaterialTheme.colorScheme.onPrimary,
                ),
                xAxisDrawer = SimpleXAxisDrawer(
                    axisLineThickness = 4.dp,
                    axisLineColor = MaterialTheme.colorScheme.secondary,
                ),
                yAxisDrawer = SimpleYAxisDrawer(
                    labelTextColor = MaterialTheme.colorScheme.onSurface,
                    axisLineColor = MaterialTheme.colorScheme.secondary,
                    axisLineThickness = 4.dp,
                    labelValueFormatter = { value ->
                        value.roundToInt().toString()
                    },
                    labelRatio = 5,
                ),
            )
        }

    }


}

/**
 * This function is responsible for the category chart.
 *
 * @param categoryData The category data
 */
@Composable
fun CategoryChart(categoryData: List<ExpenseSummaryItem>) {
    val chartData = DonutChartDataCollection(
        categoryData.mapIndexed { index, it ->
            DonutChartData(
                (it.totalExpense).toFloat(),
                graphColors[abs(index - (graphColors.size - 1))],
                it.subject,
            )
        },
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Money spent per category \n ${LocalDate.now().year}",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        if (categoryData.isEmpty() || categoryData.stream()
                .max(Comparator.comparingDouble { it.totalExpense }).get().totalExpense == 0.0
        ) {
            NoData()
        } else {
            DonutChart(
                modifier = Modifier.padding(8.dp),
                data = chartData,
                gapPercentage = if (chartData.items.size <= 1) 0f else 0.04f,
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
                        Text(
                            text,
                            style = itemTextStyle,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

    }
}

/**
 * This function is responsible for the month chart.
 *
 * @param monthData The month data
 */
@Composable
fun MonthChart(monthData: List<ExpenseSummaryItem>) {
    val chartData = BarChartData(
        bars = monthData.map {
            Bar(
                value = it.totalExpense.toFloat(),
                label = it.subject.substring(5, 7),
                color = graphColors[Random.nextInt(6)],
            )
        },

        )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = "Money spent per month \n ${LocalDate.now().year}",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        if (monthData.isEmpty() || monthData.stream()
                .max(Comparator.comparingDouble { it.totalExpense }).get().totalExpense == 0.0
        ) {
            NoData()
        } else {
            BarChart(
                barChartData = chartData,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .padding(bottom = 32.dp, start = 8.dp),
                yAxisDrawer = SimpleYAxisDrawer(
                    labelTextColor = MaterialTheme.colorScheme.onSurface,
                    axisLineColor = MaterialTheme.colorScheme.secondary,
                    axisLineThickness = 4.dp,
                    labelValueFormatter = { value ->
                        value.roundToInt().toString()
                    },
                    labelRatio = 5,
                ),
                xAxisDrawer = SimpleXAxisDrawer(
                    axisLineThickness = 4.dp,
                    axisLineColor = MaterialTheme.colorScheme.secondary,
                ),
                labelDrawer = SimpleValueDrawer(
                    drawLocation = SimpleValueDrawer.DrawLocation.XAxis,
                    labelTextColor = MaterialTheme.colorScheme.onSurface,
                    labelTextSize = 12.sp,
                ),
            )
        }

    }
}

/**
 * This function is responsible for the primary chart.
 *
 * @param totalSpent The total spent
 * @param owedSpent The owed spent
 */
@Composable
fun PrimaryChart(totalSpent: Double, owedSpent: Double) {
    val paidExpenses = totalSpent - owedSpent
    Log.i("SummaryScreen", "paidExpenses: $paidExpenses, owedExpenses: $owedSpent")
    val chartData = PieChartData(
        slices = listOf(
            PieChartData.Slice(
                value = paidExpenses.toFloat(),
                color = MaterialTheme.colorScheme.primaryContainer,
            ),
            PieChartData.Slice(
                value = owedSpent.toFloat(),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            ),
        ),
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(top = 16.dp),
    ) {
        Text(
            text = "Expenditures: \n ${CurrencyString(money = totalSpent, fractionDigits = 2)}",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        if (totalSpent == 0.0) {
            NoData()
        } else {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "normal expenses: ${
                        CurrencyString(
                            money = paidExpenses,
                            fractionDigits = 2,
                        )
                    }",
                    style = MaterialTheme.typography.displaySmall,
                )
                Text(
                    text = "owed expenses: ${
                        CurrencyString(
                            money = owedSpent,
                            fractionDigits = 2
                        )
                    }",
                    style = MaterialTheme.typography.displaySmall,
                )
            }
            PieChart(
                pieChartData = chartData,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp),
                sliceDrawer = SimpleSliceDrawer(sliceThickness = 100f),
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Card(
                    modifier = Modifier.size(16.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                ) {}
                Text(text = "normal expenses", style = MaterialTheme.typography.labelMedium)
                Spacer(modifier = Modifier.size(16.dp))
                Card(
                    modifier = Modifier.size(16.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer),
                ) {}
                Text(text = "owed expenses", style = MaterialTheme.typography.labelMedium)
            }
        }

    }
}

/**
 * This function is responsible for the summary loading.
 *
 */
@Composable
fun SummaryLoading() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimaryContainer)
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Please wait while we prepare your summary...",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
        )
    }
}

/**
 * This function is responsible for the no data view.
 *
 */
@Composable
fun NoData() {
    Text(
        text = "No data to display", style = MaterialTheme.typography.displaySmall,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 32.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.error,
    )
}
