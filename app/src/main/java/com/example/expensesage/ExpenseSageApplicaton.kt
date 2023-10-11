package com.example.expensesage

import android.app.Application
import androidx.compose.runtime.rememberCoroutineScope
import com.example.expensesage.data.AppContainer
import com.example.expensesage.data.AppDataContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

class ExpenseSageApplicaton : Application()  {
    lateinit var container: AppContainer
    private lateinit var appScope: CoroutineScope



    override fun onCreate() {
        super.onCreate()
        appScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

        container = AppDataContainer(this, appScope)
    }
}