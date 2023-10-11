package com.example.expensesage

import android.app.Application
import com.example.expensesage.data.AppContainer
import com.example.expensesage.data.AppDataContainer

class ExpenseSageApplicaton : Application()  {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}