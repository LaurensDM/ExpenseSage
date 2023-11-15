package com.example.expensesage.workers

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

/**
 * This function executes the workers.
 *
 * @param ctx The application [Context]
 */
suspend fun executeWorkers(ctx: Context) {

    val userSettings = UserSettings(DataStoreSingleton.getInstance(ctx))

    val workRequest = createBudgetWorker(userSettings.budgetFrequency.first())
    val reminderWorkRequest = createOwedReminderWorker()
    val syncWorkRequest = createSyncWorker()
    val onetimeSyncWorkRequest = createOneTimeSyncWorker()

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
    workManager.enqueueUniquePeriodicWork(
        "SyncWorker",
        ExistingPeriodicWorkPolicy.KEEP,
        syncWorkRequest
    )
    if (userSettings.firstTime.first()) {
        workManager.enqueueUniqueWork(
            "OneTimeSyncWorker",
            ExistingWorkPolicy.KEEP,
            onetimeSyncWorkRequest
        )
        Log.i("WorkersExecutor", "First time")
        userSettings.saveFirstTime(false)
    }


}

/**
 * This function creates a budget worker.
 *
 * @param interval The budget interval
 * @return A [PeriodicWorkRequest] object
 */
fun createBudgetWorker(interval: String): PeriodicWorkRequest {
    val date = LocalDate.now()
    val currentDateTime = LocalDateTime.now()
    val hour = currentDateTime.hour.toLong()
    val minute = currentDateTime.minute.toLong()
    val currentMinutes = hour * 60 + minute
    val dayMinutes = 24 * 60
    val repeatInterval: Long
    when (interval) {
        "Weekly" -> {
            val weekday = date.dayOfWeek.value.toLong()

            repeatInterval = if (weekday == 7L) {
                dayMinutes - currentMinutes + 7 * dayMinutes
            } else {
                val day = 7L - weekday - 1L
                dayMinutes - currentMinutes + day * dayMinutes
            }
        }

        "Monthly" -> {
            val monthDay = date.dayOfMonth.toLong()
            val monthLength = date.lengthOfMonth().toLong()

            repeatInterval = if (monthDay == monthLength) {
                val nextMonth = date.plusMonths(1L).lengthOfMonth().toLong()
                dayMinutes - currentMinutes + nextMonth * dayMinutes
            } else {
                val days = monthLength - monthDay - 1
                dayMinutes - currentMinutes + days * dayMinutes
            }

        }

        "Yearly" -> {
            val dayOfYear = date.dayOfYear.toLong()
            val yearLength = date.lengthOfYear().toLong()
            repeatInterval = if (yearLength - dayOfYear == 0L) {
                val nextYear = date.plusYears(1).lengthOfYear().toLong()
                dayMinutes - currentMinutes + nextYear * dayMinutes
            } else {
                val days = yearLength - dayOfYear - 1
                dayMinutes - currentMinutes + days * dayMinutes
            }

        }

        else -> repeatInterval = (7L * 24 * 60)
    }

    return PeriodicWorkRequestBuilder<BudgetWorker>(
        repeatInterval = repeatInterval,
        repeatIntervalTimeUnit = TimeUnit.MINUTES
    ).setInputData(
        Data.Builder().putLong("repeatInterval", repeatInterval)
            .putString("budgetFrequency", interval).build()
    )
        .build()
}

/**
 * This function creates a reminder worker.
 *
 * @return A [PeriodicWorkRequest] object
 */
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

/**
 * This function creates a sync worker.
 *
 * @return A [PeriodicWorkRequest] object
 */
fun createSyncWorker(): PeriodicWorkRequest {
    return PeriodicWorkRequestBuilder<SyncWorker>(
        repeatInterval = 2L,
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
 * This function creates a one time sync worker.
 *
 * @return A [OneTimeWorkRequest] object
 */
fun createOneTimeSyncWorker(): OneTimeWorkRequest {
    return OneTimeWorkRequestBuilder<SyncWorker>()
        .setConstraints(
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
suspend fun changeInterval(ctx: Context) {
    val workManager = WorkManager.getInstance(ctx)
    workManager.cancelUniqueWork("BudgetUpdateWorker")

    val userSettings = UserSettings(DataStoreSingleton.getInstance(ctx))

    val workRequest = createBudgetWorker(userSettings.budgetFrequency.first())

    WorkManager.getInstance(ctx)
        .enqueueUniquePeriodicWork("BudgetWorker", ExistingPeriodicWorkPolicy.KEEP, workRequest)
}
