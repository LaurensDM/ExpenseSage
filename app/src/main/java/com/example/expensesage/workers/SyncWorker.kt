package com.example.expensesage.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import com.example.expensesage.network.CurrencyApiExecutor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.IOException


/**
 * This class is responsible for syncing currencies.
 *
 * @constructor
 * Creates a new SyncWorker.
 *
 * @param ctx The application [Context]
 * @param params Parameters to setup the internal state of this worker
 */
class SyncWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    private val currencyRepository =
        AppDataContainer(ctx, CoroutineScope(Dispatchers.IO)).currencyRepository
    private val userSettings = UserSettings(DataStoreSingleton.getInstance(ctx))
    private val api = CurrencyApiExecutor(userSettings)

    /**
     * This function is called on the background thread, so it is safe to do long running operations here.
     *
     * @return [Result.success] if the work was successfully completed,
     */
    override suspend fun doWork(): Result {
        try {
            sync()
        } catch (e: IOException) {
            Log.e("SyncWorker", "Error syncing currencies", e)
            return Result.failure()
        }

        return Result.success()
    }

    /**
     * This function syncs currencies.
     *
     */
    private suspend fun sync() {
        val currencies = api.getCurrencyRates()

        Log.d("SyncWorker", "Syncing ${currencies.size} currencies")

        if (currencies.isNotEmpty()) {
            currencyRepository.insertAll(currencies)
        }
    }
}