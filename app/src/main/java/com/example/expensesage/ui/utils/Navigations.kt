package com.example.expensesage.ui.utils

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AssignmentLate
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.InsertChartOutlined
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.expensesage.R

/**
 * Enum class that is used to store navigation information
 *
 * @property route The route of navigation
 * @property title The title of navigation
 * @property icon The icon of navigation
 */
enum class Navigations(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    Start(
        route = "Start",
        title =  R.string.app_name,
        icon = Icons.Default.AccountBalance
    ),
    Expenses(
        route = "Expenses",
        title = R.string.expense,
        icon = Icons.Default.Paid
    ),
    Owed(
        route = "Owed",
        title = R.string.owed,
        icon = Icons.Default.AssignmentLate
    ),
    Currencies(
        route = "Currencies",
        title = R.string.currencyInfo,
        icon = Icons.Default.CurrencyExchange
    ),
    Summary(
        route = "Summary",
        title = R.string.summary,
        icon = Icons.Default.InsertChartOutlined
    ),
    Settings(
        route = "Settings",
        title = R.string.settings,
        icon = Icons.Default.Settings
    ),
    Edit(
        route = "Edit/{id}",
        title = R.string.settings,
        icon = Icons.Default.Create
    )
}