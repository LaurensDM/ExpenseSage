package com.example.expensesage.network

import android.util.Log
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.JsonObject

class CurrencyApiExecutor(private val userSettings: UserSettings) {
    suspend fun getCurrencyRates(): JsonObject {
        val currency = userSettings.currency.first()
        return CurrencyApi.retrofitService.getCurrencyRates(currency.lowercase())

    }
    suspend fun getRate(currency: String): Double {
        if (currency.equals("EUR", ignoreCase = true)) return 1.0
        val response = CurrencyApi.retrofitService.getRate(currency.lowercase())
        Log.d("CurrencyApiExecutor", "${response[currency.lowercase()]}")
        return response[currency.lowercase()].toString().toDouble()
    }
}
