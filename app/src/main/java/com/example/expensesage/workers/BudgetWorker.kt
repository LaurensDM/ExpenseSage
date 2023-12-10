package com.example.expensesage.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first

/**
 *
 *
 * @constructor
 *  Creates a new BudgetWorker.
 *
 * @param ctx The application [Context]
 * @param params Parameters to setup the internal state of this worker
 */
class BudgetWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    private val userSettings: UserSettings =
        UserSettings(DataStoreSingleton.getInstance(context = ctx))

    /**
     *  This function is called on the background thread, so it is safe to do long running operations here.
     *
     * @return [Result.success] if the work was successfully completed,
     */
    override suspend fun doWork(): Result {

        updateBudget()
        changeInterval(applicationContext, userSettings)
        return Result.success()
    }

    /**
     * This function updates the budget based on the budget frequency.
     *
     *
     */
    private suspend fun updateBudget() {
        val budget = userSettings.budget.first()
        userSettings.saveMoneyAvailable(budget)

        makeStatusNotification(
            1,
            "ExpenseSage",
            "Your money has been reset to $budget",
            applicationContext
        )
    }
}

//class BudgetUpdateReceiver : BroadcastReceiver() {
//    override fun onReceive(context: Context?, intent: Intent?) {
//        val workManager = WorkManager.getInstance(context!!)
//        val intervalType = intent?.getIntExtra("intervalType", Interval.WEEKLY) ?: Interval.WEEKLY
//
//        val workRequest = OneTimeWorkRequestBuilder<BudgetWorker>()
//            .setConstraints(Constraints.NONE)
//            .build()
//
//        workManager.enqueueUniqueWork(
//            "BudgetWorker",
//            ExistingWorkPolicy.KEEP,
//            workRequest
//        )
//
//        Log.d("BudgetUpdateReceiver", "Budget update scheduled for interval: $intervalType")
//    }
//}
//
//object Interval {
//    const val WEEKLY = 0
//    const val MONTHLY = 1
//    const val YEARLY = 2
//}
//
//fun scheduleBudgetUpdate(context: Context, selectedInterval: Int) {
//    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//    val intent = Intent("com.example.expensesage.BUDGET_UPDATE")
//    intent.putExtra("intervalType", selectedInterval)
//    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//    val calendar = Calendar.getInstance().apply {
//        add(Calendar.MINUTE, 5)
////        when (selectedInterval) {
////            Interval.WEEKLY -> {
////                set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY)
////                set(Calendar.HOUR_OF_DAY, 0)
////                set(Calendar.MINUTE, 0)
////                set(Calendar.SECOND, 0)
////                set(Calendar.MILLISECOND, 0)
////            }
////            Interval.MONTHLY -> {
////                set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
////                set(Calendar.HOUR_OF_DAY, 0)
////                set(Calendar.MINUTE, 0)
////                set(Calendar.SECOND, 0)
////                set(Calendar.MILLISECOND, 0)
////            }
////            Interval.YEARLY -> {
////                set(Calendar.DAY_OF_YEAR, getActualMaximum(Calendar.DAY_OF_YEAR))
////                set(Calendar.HOUR_OF_DAY, 0)
////                set(Calendar.MINUTE, 0)
////                set(Calendar.SECOND, 0)
////                set(Calendar.MILLISECOND, 0)
////            }
////        }
//    }
//
//    val intervalMillis: Long = when (selectedInterval) {
////        Interval.WEEKLY -> AlarmManager.INTERVAL_DAY * 7
////        Interval.MONTHLY -> AlarmManager.INTERVAL_DAY * calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
////        Interval.YEARLY -> AlarmManager.INTERVAL_DAY * calendar.getActualMaximum(Calendar.DAY_OF_YEAR)
////        else -> AlarmManager.INTERVAL_DAY * 7
//        Interval.WEEKLY -> 5 * 60 * 1000  // 5 minutes
//        Interval.MONTHLY -> 5 * 60 * 1000  // 5 minutes
//        Interval.YEARLY -> 5 * 60 * 1000  // 5 minutes
//        else -> 5 * 60 * 1000  // Default to 5 minutes
//    }
//
//    alarmManager.setRepeating(
//        AlarmManager.RTC_WAKEUP,
//        calendar.timeInMillis,
//        intervalMillis,
//        pendingIntent
//    )
//
//    Log.d("BudgetUpdateReceiver", "Budget update alarm scheduled for interval: $selectedInterval")
//}