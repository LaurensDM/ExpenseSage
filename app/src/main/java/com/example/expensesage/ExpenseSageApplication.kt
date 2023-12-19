package com.example.expensesage

import android.app.Application
import com.example.expensesage.data.AppContainer
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.UserSettingsService
//import com.example.expensesage.workers.Interval
import com.example.expensesage.workers.executeWorkers
//import com.example.expensesage.workers.scheduleBudgetUpdate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * This class is responsible for setting up the application.
 *
 * @constructor
 * Creates a new ExpenseSageApplication.
 *
 */
class ExpenseSageApplication : Application() {
    lateinit var userSettings: UserSettingsService
    lateinit var container: AppContainer
    private lateinit var appScope: CoroutineScope

    override fun onCreate() {
        super.onCreate()

        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        userSettings = UserSettings(DataStoreSingleton.getInstance(context = this))
        container = AppDataContainer(this, appScope)
        appScope.launch(Dispatchers.IO) {
            if (userSettings.firstTime.first()) {
                executeWorkers(this@ExpenseSageApplication, userSettings)
                userSettings.saveFirstTime(false)
//                scheduleBudgetUpdate(this@ExpenseSageApplication, Interval.WEEKLY)
            }
        }
    }
}
