package com.example.expensesage.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.AppDataContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first

private const val TAG = "OwedReminderWorker"

class OwedReminderWorker(ctx: Context, params: WorkerParameters): CoroutineWorker(ctx, params) {

    private val database = AppDataContainer(ctx, CoroutineScope(Dispatchers.IO)).expenseRepository
    override suspend fun doWork(): Result {
        sendReminder()
        return Result.success()
    }

    private suspend  fun sendReminder() {
        val expenses = database.getExpenses(true).first().size
        if (expenses > 0) {
            makeStatusNotification(2,"OwedReminderWorker", "You still have $expenses expenses to pay", applicationContext)
        }
    }
}