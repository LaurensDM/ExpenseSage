package com.example.expensesage.workers

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.expensesage.data.AppContainer
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.first

private const val TAG = "BudgetWorker"
class BudgetWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {

//    private val appContainer: AppContainer = AppDataContainer(ctx, CoroutineScope(IO))
//    private val Context.dataStore: DataStore<Preferences> by
    private val userSettings: UserSettings =  UserSettings(DataStoreSingleton.getInstance(context = ctx))
    override suspend fun doWork(): Result {
        makeStatusNotification(1,"BudgetWorker", "BudgetWorker is running", applicationContext)
        updateBudget()
        return Result.success()
    }

    private suspend fun updateBudget(){
        val budget = userSettings.budget.first()
        Log.d(TAG, "updateBudget: $budget")
    }
}