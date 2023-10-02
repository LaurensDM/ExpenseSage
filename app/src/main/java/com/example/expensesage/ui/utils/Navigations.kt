package com.example.expensesage.ui.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AssignmentLate
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.InsertChartOutlined
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.expensesage.R

enum class Navigations(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    Start(
        route = "start",
        title =  R.string.app_name,
        icon = Icons.Default.AccountBalance
    ),
    Expenses(
        route = "expenses",
        title = R.string.expense,
        icon = Icons.Default.Paid
    ),
    Owed(
        route = "owed",
        title = R.string.app_name,
        icon = Icons.Default.CurrencyExchange
    ),
    Due(
        route = "due",
        title = R.string.app_name,
        icon = Icons.Default.AssignmentLate
    ),
    Summary(
        route = "summary",
        title = R.string.app_name,
        icon = Icons.Default.InsertChartOutlined
    ),
    Settings(
        route = "settings",
        title = R.string.app_name,
        icon = Icons.Default.Settings
    )
}