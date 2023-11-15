package com.example.expensesage.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.AppDataContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first

private const val TAG = "OwedReminderWorker"

/**
 *  This class is responsible for sending a notification to the user if they have any expenses to pay.
 *
 * @constructor
 * Creates a new OwedReminderWorker.
 *
 * @param ctx The application [Context]
 * @param params Parameters to setup the internal state of this worker
 */
class OwedReminderWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    private val database = AppDataContainer(ctx, CoroutineScope(Dispatchers.IO)).expenseRepository

    /**
     * This function is called on the background thread, so it is safe to do long running operations here.
     *
     * @return [Result.success] if the work was successfully completed,
     */
    override suspend fun doWork(): Result {
        sendReminder()
        return Result.success()
    }

    /**
     * This function sends a notification to the user if they have any expenses to pay.
     *
     */
    private suspend  fun sendReminder() {
        val expenses = database.getExpenses(true).first().size
        if (expenses > 0) {
            makeStatusNotification(2,"OwedReminderWorker", "You still have $expenses expenses to pay", applicationContext)
        }
    }
}