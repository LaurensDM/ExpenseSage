package com.example.expensesage.network

import android.util.Log
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.currencies.Currency
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Currency API Executor
 *
 * @property userSettings UserSettings User settings
 */
class CurrencyApiExecutor(private val userSettings: UserSettings) {
    /**
     * Get currency rates
     *
     * @return JsonObject Currency rates
     */
    suspend fun getCurrencyRates(): List<Currency> {
        val currency = userSettings.currency.first()
        val onlineCurrencies = CurrencyApi.retrofitService.getCurrencyRates(currency.lowercase())
        val date = onlineCurrencies["date"]?.jsonPrimitive?.content ?: ""
        val currencies = onlineCurrencies[currency.lowercase()]?.jsonObject?.entries?.map {
            Currency(
                currencyCode = it.key,
                rate = it.value.jsonPrimitive.content.toDouble(),
                date = date,
                comparedCurrency = currency.lowercase()
            )
        } ?: emptyList()

        return currencies;
    }

    /**
     * Get rate for currency (e.g. USD)
     *
     * @param currency String Currency code (e.g. USD)
     * @return Double Rate for currency
     */
    suspend fun getRate(currency: String): Double {
        if (currency.equals("EUR", ignoreCase = true)) return 1.0
        val response = CurrencyApi.retrofitService.getRate(currency.lowercase())
        Log.d("CurrencyApiExecutor", "${response[currency.lowercase()]}")
        return response[currency.lowercase()].toString().toDouble()
    }
}
