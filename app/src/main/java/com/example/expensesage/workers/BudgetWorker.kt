package com.example.expensesage.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first

private const val TAG = "BudgetWorker"

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

    //    private val appContainer: AppContainer = AppDataContainer(ctx, CoroutineScope(IO))
//    private val Context.dataStore: DataStore<Preferences> by
    private val userSettings: UserSettings =
        UserSettings(DataStoreSingleton.getInstance(context = ctx))
    private val context = ctx

    /**
     *  This function is called on the background thread, so it is safe to do long running operations here.
     *
     * @return [Result.success] if the work was successfully completed,
     */
    override suspend fun doWork(): Result {
        updateBudget()
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
        val interval = inputData.getLong("interval", 0L)

        when (inputData.getString("budgetFrequency")) {
            "Weekly" -> {
                if (interval < 24*7L*60) {
                    changeInterval(context)
                }
            }

            "Monthly" -> {
                changeInterval(context)
            }

            "Yearly" -> {
                changeInterval(context)
            }
        }
//
        makeStatusNotification(
            1,
            "ExpenseSage",
            "Your money has been reset to $budget",
            applicationContext
        )
        Log.d(TAG, "updateBudget: $budget")
    }
}