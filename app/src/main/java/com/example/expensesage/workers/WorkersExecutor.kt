package com.example.expensesage.workers

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.expensesage.data.UserSettingsService
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Random
import java.util.concurrent.TimeUnit

/**
 * This function executes the workers.
 *
 * @param ctx The application [Context]
 */

    suspend fun executeWorkers( ctx: Context, userSettings: UserSettingsService) {
        val workManager = WorkManager.getInstance(ctx)

        val workRequest = createBudgetWorker(userSettings.budgetFrequency.first())
        val workRequest2 = createOwedReminderWorker()
        val workRequest3 = createSyncWorker()

        workManager.enqueueUniquePeriodicWork(
            "BudgetWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest
        )
        workManager.enqueueUniquePeriodicWork(
            "OwedReminderWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest2
        )
        workManager.enqueueUniquePeriodicWork(
            "SyncWorker",
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest3
        )

    }

    /**
     * This function creates a budget worker.
     *
     * @param interval The budget interval
     * @return A [PeriodicWorkRequest] object
     */
    private fun createBudgetWorker(interval: String): PeriodicWorkRequest {
        val date = LocalDate.now()
        val currentDateTime = LocalDateTime.now()
        val hour = currentDateTime.hour.toLong()
        val minute = currentDateTime.minute.toLong()
        val currentMinutes = hour * 60 + minute
        val dayMinutes = 1440L
        val repeatInterval: Long
        when (interval) {
            "Weekly" -> {
                val weekday = date.dayOfWeek.value.toLong()
                Log.i( "BudgetWorker", "weekday: $weekday")
                Log.i( "BudgetWorker", "currentDate: $currentDateTime")
                Log.i( "BudgetWorker", "hour: $hour")
                Log.i( "BudgetWorker", "minute: $minute")
                repeatInterval = if (weekday == 7L) {
                    dayMinutes - currentMinutes
                } else {
                    val day = 7L - weekday
                    dayMinutes - currentMinutes + day * dayMinutes
                }
            }

            "Monthly" -> {
                val monthDay = date.dayOfMonth.toLong()
                val monthLength = date.lengthOfMonth().toLong()

                repeatInterval = if (monthDay == monthLength) {
                    dayMinutes - currentMinutes
                } else {
                    val days = monthLength - monthDay
                    dayMinutes - currentMinutes + days * dayMinutes
                }

            }

            "Yearly" -> {
                val dayOfYear = date.dayOfYear.toLong()
                val yearLength = date.lengthOfYear().toLong()
                repeatInterval = if (yearLength - dayOfYear == 0L) {
                    dayMinutes - currentMinutes
                } else {
                    val days = yearLength - dayOfYear
                    dayMinutes - currentMinutes + days * dayMinutes
                }

            }

            else -> repeatInterval = (7L * 24 * 60)
        }

        Log.i("BudgetWorker", "currentMinutes: $currentMinutes")
        Log.i("BudgetWorker", "interval: $interval")
        Log.i("BudgetWorker", "repeatInterval: $repeatInterval")
        return PeriodicWorkRequestBuilder<BudgetWorker>(
            repeatInterval = repeatInterval,
            repeatIntervalTimeUnit = TimeUnit.MINUTES,
            flexTimeInterval = 5L,
            flexTimeIntervalUnit = TimeUnit.MINUTES
        ).build()
    }

    /**
     * This function creates a reminder worker.
     *
     * @return A [PeriodicWorkRequest] object
     */
    private fun createOwedReminderWorker(): PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<OwedReminderWorker>(
            repeatInterval = 3L,
            repeatIntervalTimeUnit = TimeUnit.DAYS,
            flexTimeInterval = 5L,
            flexTimeIntervalUnit = TimeUnit.MINUTES
        ).setConstraints(
            Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                //.setRequiresDeviceIdle(true)
                .build()
        )
            .build()
    }

    /**
     * This function creates a sync worker.
     *
     * @return A [PeriodicWorkRequest] object
     */
    private fun createSyncWorker(): PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<SyncWorker>(
            repeatInterval = 1L,
            repeatIntervalTimeUnit = TimeUnit.DAYS
        ).setConstraints(
            Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.NOT_ROAMING)
                .build()
        )
            .build()
    }

    /**
     * This function changes the interval of the budget worker.
     *
     * @param ctx The application [Context]
     */
    suspend fun changeInterval(ctx: Context, userSettings: UserSettingsService) {
        val workManager = WorkManager.getInstance(ctx)

        workManager.cancelUniqueWork("BudgetWorker")

        val workRequest = createBudgetWorker(userSettings.budgetFrequency.first())

        workManager.enqueueUniquePeriodicWork("BudgetWorker", ExistingPeriodicWorkPolicy.KEEP, workRequest)
    }


