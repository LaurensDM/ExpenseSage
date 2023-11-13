package com.example.expensesage.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.currencies.Currency
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class SyncWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    private val currencyRepository = AppDataContainer(ctx, CoroutineScope(Dispatchers.IO)).currencyRepository
    private val userSettings = UserSettings(DataStoreSingleton.getInstance(ctx))
    private val api = CurrencyApiExecutor(userSettings)
    override suspend fun doWork(): Result {
        try {
            sync()
        } catch (e: Exception) {
            Log.e("SyncWorker", "Error syncing currencies", e)
            return Result.failure()
        }

        return Result.success()
    }

    private suspend fun sync() {
        makeStatusNotification(3,"ExpenseSage", "Syncing currencies...", applicationContext)

        val currency = userSettings.currency.first()
        val currenciesJson = api.getCurrencyRates()
        val date = currenciesJson["date"]?.jsonPrimitive?.content ?: ""
        val currencies = currenciesJson[currency.lowercase()]?.jsonObject?.entries?.map {
            Currency(
                currencyCode = it.key,
                date = date,
                rate = it.value.jsonPrimitive.content.toDouble(),
                comparedCurrency = currency.lowercase()
            )
        } ?: emptyList()
        Log.d("SyncWorker", "Syncing ${currencies.size} currencies")
        currencyRepository.insertAll(currencies)
    }
}