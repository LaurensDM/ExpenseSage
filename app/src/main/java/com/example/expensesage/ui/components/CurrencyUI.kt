package com.example.expensesage.ui.components

import android.icu.text.NumberFormat
import android.icu.util.Currency
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expensesage.ui.AppViewModelProvider
import com.example.expensesage.ui.viewModels.SettingsViewModel
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CurrencyIcon(currency: StateFlow<String>) {
    val currentCurrency by currency.collectAsState()
    when (currentCurrency) {
        "EUR" -> Icon(Icons.Filled.Euro, contentDescription = "Euro")
        "USD" -> Icon(Icons.Filled.AttachMoney, contentDescription = "US Dollar")
        "JPY" -> Icon(Icons.Filled.CurrencyYen, contentDescription = "Japanese Yen")
    }
}

@Composable
fun CurrencyText(currency: StateFlow<String>, moneyAvailable: StateFlow<Double>, currencyModifier: StateFlow<Double>) {
    val currentCurrency by currency.collectAsState()
    val currentMoney by moneyAvailable.collectAsState()
    val currentModifier by currencyModifier.collectAsState()

    val formattedMoney = formatMoney(currentMoney * currentModifier, currentCurrency, 2)

    when (currentCurrency) {
        "EUR" -> return Text(text = "You have $formattedMoney  left", style = MaterialTheme.typography.headlineMedium)
        "USD" -> return Text(text = "You have $formattedMoney left", style = MaterialTheme.typography.headlineMedium)
        "JPY" -> return Text(text = "You have $formattedMoney left", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun CurrencyString(money: Double, fractionDigits: Int, viewModel: SettingsViewModel = viewModel(factory = AppViewModelProvider.Factory)): String {
    val currentCurrency by viewModel.getCurrency().collectAsState()
    val currentModifier by viewModel.getCurrencyModifier().collectAsState()

    val currentMoney = formatMoney(money * currentModifier, currentCurrency, fractionDigits)

    when (currentCurrency) {
        "EUR" -> return "$currentMoney"
        "USD" -> return "$currentMoney"
        "JPY" -> return "$currentMoney"
    }

    return "€ $currentMoney"
}

fun formatMoney(currentMoney: Double, currency: String, fractionDigits: Int): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = fractionDigits

    if (currency.isNotEmpty()) {
        format.currency = Currency.getInstance(currency)
    }

    return format.format(currentMoney).replace(".", ",").replaceFirst(",", ".")
}
