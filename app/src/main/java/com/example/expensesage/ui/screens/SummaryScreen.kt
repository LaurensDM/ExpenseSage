package com.example.expensesage.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.example.expensesage.ui.theme.Slice1
import com.example.expensesage.ui.theme.Slice2
import com.example.expensesage.ui.theme.Slice3
import com.example.expensesage.ui.theme.Slice4
import com.example.expensesage.ui.theme.Slice5
import com.example.expensesage.ui.theme.itemTextStyle
import com.example.expensesage.ui.theme.moneyAmountStyle
import com.example.expensesage.ui.utils.DonutChartData
import com.example.expensesage.ui.utils.DonutChartDataCollection
import com.example.expensesage.ui.viewModels.SettingsViewModel
import com.example.expensesage.ui.viewModels.StatisticViewModel

@Composable
fun SummaryScreen(viewModel: StatisticViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val moneySpent by viewModel.getMoneySpent().collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = "Money spent by category",
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Center,
        )
        Chart()
        Text(
            text = "Total money spent since using this app: ${
                CurrencyString(
                    money = 4000.0,
                    fractionDigits = 2,
                )
            }",
            style = MaterialTheme.typography.displayMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun Chart(viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val chartData = DonutChartDataCollection(
        listOf(
            DonutChartData(1200.0f, Slice1, title = "Food & Groceries"),
            DonutChartData(1500.0f, Slice2, title = "Rent"),
            DonutChartData(300.0f, Slice3, title = "Gas"),
            DonutChartData(700.0f, Slice4, title = "Online Purchases"),
            DonutChartData(300.0f, Slice5, title = "Clothing"),
        ),
    )
    DonutChart(modifier = Modifier.padding(8.dp), data = chartData) { selected ->
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
