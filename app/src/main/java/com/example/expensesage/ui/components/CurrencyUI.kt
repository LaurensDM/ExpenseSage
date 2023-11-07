package com.example.expensesage.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CurrencyYen
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.data.currencyList
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.viewModels.SettingsViewModel
import kotlinx.coroutines.flow.StateFlow
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Currency

@Composable
fun CurrencyIcon(viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val currentCurrency by viewModel.getCurrency().collectAsState()
    when (currentCurrency) {
        "EUR" -> Icon(Icons.Filled.Euro, contentDescription = "Euro")
        "USD" -> Icon(Icons.Filled.AttachMoney, contentDescription = "US Dollar")
        "JPY" -> Icon(Icons.Filled.CurrencyYen, contentDescription = "Japanese Yen")
    }
}

@Composable
fun CurrencyText(
    currency: StateFlow<String>,
    moneyAvailable: StateFlow<Double>,
    currencyModifier: StateFlow<Double>
) {
    val currentCurrency by currency.collectAsState()
    val currentMoney by moneyAvailable.collectAsState()
    val currentModifier by currencyModifier.collectAsState()

    val formattedMoney = formatMoney(currentMoney * currentModifier, currentCurrency, 2)

    val color = if (currentMoney > 0) {
        MaterialTheme.colorScheme.onSecondaryContainer
    } else {
        MaterialTheme.colorScheme.error
    }

    return Text(
        text = "You have $formattedMoney  left",
        style = MaterialTheme.typography.displayLarge,
        color = color,
        textAlign = TextAlign.Center
    )
}

@Composable
fun CurrencyString(
    money: Double,
    fractionDigits: Int,
    viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)
): String {
    val currentCurrency by viewModel.getCurrency().collectAsState()
    val currentModifier by viewModel.getCurrencyModifier().collectAsState()

    val currentMoney = formatMoney(money * currentModifier, currentCurrency, fractionDigits)

    if (currencyList.contains(currentCurrency)) {
        return currentMoney
    }

    return "€ $currentMoney"
}

fun formatMoney(currentMoney: Double, currency: String, fractionDigits: Int): String {
    val format = NumberFormat.getCurrencyInstance()
    val symbols = DecimalFormatSymbols()

    // Set the thousands separator to a period (.)
    symbols.groupingSeparator = '.'

    // Set the decimal separator to a comma (,)
    symbols.decimalSeparator = ','

    format.isGroupingUsed = true
    format.maximumFractionDigits = fractionDigits

    if (currency.isNotEmpty()) {
        format.currency = Currency.getInstance(currency)
    }

    return format.format(currentMoney)
}
