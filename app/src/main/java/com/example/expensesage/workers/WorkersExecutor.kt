package com.example.expensesage.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.util.concurrent.TimeUnit

suspend fun executeWorkers(ctx: Context) {

    val userSettings = UserSettings(DataStoreSingleton.getInstance(ctx))

    val workRequest = createBudgetWorker(userSettings.budgetFrequency.first())

    val reminderWorkRequest = createOwedReminderWorker()

    val workManager = WorkManager.getInstance(ctx)
    workManager.enqueueUniquePeriodicWork(
        "BudgetWorker",
        ExistingPeriodicWorkPolicy.KEEP,
        workRequest
    )
    workManager.enqueueUniquePeriodicWork(
        "OwedReminderWorker",
        ExistingPeriodicWorkPolicy.KEEP,
        reminderWorkRequest
    )
}

fun cancelWorkers(ctx: Context) {
    val workManager = WorkManager.getInstance(ctx)
    workManager.cancelUniqueWork("BudgetUpdateWorker")
}

fun createBudgetWorker(interval: String): PeriodicWorkRequest {
    val date = LocalDate.now()
    val repeatInterval = when (interval) {
        "Weekly" -> {
            val weekday = date.dayOfWeek.value.toLong()

            if (weekday == 7L) {
                1L
            } else {
                7L - weekday
            }
        }

        "Monthly" -> {
            val monthDay = date.dayOfMonth.toLong()
            val monthLength = date.lengthOfMonth().toLong()
            if (monthDay == monthLength) {
                val nextMonth = date.plusMonths(1L).lengthOfMonth().toLong()

                nextMonth + 1L
            } else {
                monthLength - monthDay
            }

        }

        "Yearly" -> {
            if (date.lengthOfYear().toLong() - date.dayOfYear.toLong() == 0L) {
                val nextYear = date.plusYears(1).lengthOfYear().toLong()
                nextYear + 1L
            } else {
                date.lengthOfYear().toLong() - date.dayOfYear.toLong()
            }

        }

        else -> 7L
    }

    return PeriodicWorkRequestBuilder<BudgetWorker>(
        repeatInterval = repeatInterval,
        repeatIntervalTimeUnit = TimeUnit.DAYS
    ).build()
}

fun createOwedReminderWorker(): PeriodicWorkRequest {
    return PeriodicWorkRequestBuilder<OwedReminderWorker>(
        repeatInterval = 3L,
        repeatIntervalTimeUnit = TimeUnit.DAYS
    ).setConstraints(
        Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresDeviceIdle(true)
            .build()
    )
        .build()
}

suspend fun changeInterval(ctx: Context) {
    val workManager = WorkManager.getInstance(ctx)
    workManager.cancelUniqueWork("BudgetUpdateWorker")

    val userSettings = UserSettings(DataStoreSingleton.getInstance(ctx))

    val workRequest = createBudgetWorker(userSettings.budgetFrequency.first())

    WorkManager.getInstance(ctx)
        .enqueueUniquePeriodicWork("BudgetWorker", ExistingPeriodicWorkPolicy.KEEP, workRequest)
}
