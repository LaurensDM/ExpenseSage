package com.example.expensesage

import android.app.Application
import com.example.expensesage.data.AppContainer
import com.example.expensesage.data.AppDataContainer
import com.example.expensesage.data.DataStoreSingleton
import com.example.expensesage.data.UserSettings
import com.example.expensesage.network.CurrencyApiExecutor
import com.example.expensesage.workers.executeWorkers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

private const val SETTINGS_PREFERENCE_NAME = "settings_preferences"

class ExpenseSageApplication : Application() {
    lateinit var userSettings: UserSettings
    lateinit var container: AppContainer
    private lateinit var appScope: CoroutineScope
    lateinit var currencyExecutor: CurrencyApiExecutor

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            executeWorkers(this@ExpenseSageApplication)
        }

        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
        userSettings = UserSettings(DataStoreSingleton.getInstance(context = this))
        container = AppDataContainer(this, appScope)
        currencyExecutor = CurrencyApiExecutor(userSettings)
    }
}
