package com.example.expensesage.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkManager
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
        WorkManager.getInstance(applicationContext).cancelAllWorkByTag("BudgetWorker")
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