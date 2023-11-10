package com.example.expensesage.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.flow.first

private const val TAG = "BudgetWorker"

class BudgetWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

    //    private val appContainer: AppContainer = AppDataContainer(ctx, CoroutineScope(IO))
//    private val Context.dataStore: DataStore<Preferences> by
    private val userSettings: UserSettings =
        UserSettings(DataStoreSingleton.getInstance(context = ctx))
    private val context = ctx
    override suspend fun doWork(): Result {
        updateBudget()
        return Result.success()
    }

    private suspend fun updateBudget() {
        val budget = userSettings.budget.first()
        userSettings.saveMoneyAvailable(budget)
        val interval = inputData.getLong("interval", 0L)

        when (inputData.getString("budgetFrequency")) {
            "Weekly" -> {
                if (interval < 7L) {
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