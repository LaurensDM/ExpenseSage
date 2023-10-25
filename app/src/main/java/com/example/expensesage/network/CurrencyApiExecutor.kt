package com.example.expensesage.network

import android.util.Log
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.JsonObject

class CurrencyApiExecutor(private val userSettings: UserSettings) {
    suspend fun getCurrencyRates(): JsonObject {
        val currency = userSettings.currency.first()

        when (currency) {
            "EUR" -> {
                Log.i("CurrencyApiExecutor", "getCurrencyRates: EUR")
                val result = CurrencyApi.retrofitService.getEurRate()
                Log.i("CurrencyApiExecutor", "getCurrencyRates: $result")
                return result
            }
            "USD" -> {
                Log.i("CurrencyApiExecutor", "getCurrencyRates: USD")
                val result = CurrencyApi.retrofitService.getUsdRate()
                Log.i("CurrencyApiExecutor", "getCurrencyRates: $result")
                return result
            }
            "JPY" -> {
                Log.i("CurrencyApiExecutor", "getCurrencyRates: USD")
                val result = CurrencyApi.retrofitService.getJpyRate()
                Log.i("CurrencyApiExecutor", "getCurrencyRates: $result")
                return result
            }
        }
        Log.i("CurrencyApiExecutor", "Outside of when: $currency")
        return CurrencyApi.retrofitService.getEurRate()
    }
}
