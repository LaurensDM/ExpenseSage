package com.example.expensesage

import android.app.Application
import com.example.expensesage.data.AppContainer
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import com.example.expensesage.data.UserSettingsService
import com.example.expensesage.workers.executeWorkers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
        appScope.launch{
            executeWorkers(
                this@ExpenseSageApplication,
                UserSettings(DataStoreSingleton.getInstance(this@ExpenseSageApplication))
            )
        }

    }
}
